package com.weijinchuan.todolist.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weijinchuan.todolist.common.convention.execption.ClientException;
import com.weijinchuan.todolist.dao.entity.UserDO;
import com.weijinchuan.todolist.dao.mapper.UserMapper;
import com.weijinchuan.todolist.dto.req.UserLoginReqDTO;
import com.weijinchuan.todolist.dto.req.UserRegisterReqDTO;
import com.weijinchuan.todolist.dto.resp.UserLoginRespDTO;
import com.weijinchuan.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.redisson.api.RBloomFilter;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.weijinchuan.todolist.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.weijinchuan.todolist.common.enums.UserErrorCodeEnum.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate userLoginRedisTemplate;

    @Override
    public boolean hasUserName(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (hasUserName(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }

        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());

        try {
            if (lock.tryLock()) {
                try {
                    int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                    if (inserted < 1) {
                        throw new ClientException(USER_SAVE_ERROR);
                    }
                } catch (DuplicateKeyException ex) {
                    throw new ClientException(USER_EXIST);
                }

                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);

        UserDO userDO = baseMapper.selectOne(queryWrapper);

        if (userDO == null) {
            throw new ClientException("用户不存在");
        }

        Boolean hasLogin = userLoginRedisTemplate.hasKey("login_" + requestParam.getUsername());
        if (hasLogin != null && hasLogin) {
            throw new ClientException("用户已登陆");
        }

        String uuid = UUID.randomUUID().toString();
        userLoginRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), uuid, JSON.toJSON(userDO));
        userLoginRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.MINUTES);

        return new UserLoginRespDTO(uuid);
    }
}
