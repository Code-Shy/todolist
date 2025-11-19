package com.weijinchuan.todolist.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weijinchuan.todolist.common.convention.execption.ClientException;
import com.weijinchuan.todolist.dao.entity.TodoListDO;
import com.weijinchuan.todolist.dao.entity.UserDO;
import com.weijinchuan.todolist.dao.mapper.TodoListMapper;
import com.weijinchuan.todolist.dto.req.TodoListAddReqDTO;
import com.weijinchuan.todolist.dto.req.TodoListDeleteReqDTO;
import com.weijinchuan.todolist.dto.req.TodoListUpdateReqDTO;
import com.weijinchuan.todolist.dto.resp.TodoListAddRespDTO;
import com.weijinchuan.todolist.dto.resp.TodoListDeleteRespDTO;
import com.weijinchuan.todolist.dto.resp.TodoListRespDTO;
import com.weijinchuan.todolist.dto.resp.TodoListUpdateRespDTO;
import com.weijinchuan.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.weijinchuan.todolist.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl extends ServiceImpl<TodoListMapper, TodoListDO> implements TodoListService {

    @Override
    public void addTodoList(TodoListAddReqDTO requestParam) {
        int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, TodoListDO.class));
        if (inserted < 1) {
            throw new ClientException(USER_SAVE_ERROR);
        }
    }

    @Override
    public void updateTodoList(TodoListUpdateReqDTO requestParam) {
        LambdaQueryWrapper<TodoListDO> queryWrapper = Wrappers.lambdaQuery(TodoListDO.class)
                .eq(TodoListDO::getTitle, requestParam.getTitle());

        baseMapper.update(BeanUtil.toBean(requestParam, TodoListDO.class), queryWrapper);
    }

    @Override
    public void deleteTodoList(TodoListDeleteReqDTO requestParam) {
        LambdaUpdateWrapper<TodoListDO> updateWrapper = Wrappers.lambdaUpdate(TodoListDO.class)
                .eq(TodoListDO::getTitle, requestParam.getTitle())
                .set(TodoListDO::getDelFlag, 1);

        baseMapper.update(null, updateWrapper);
    }

    @Override
    public TodoListRespDTO getTodoListByTitle(String title) {
        LambdaQueryWrapper<TodoListDO> queryWrapper = Wrappers.lambdaQuery(TodoListDO.class)
                .eq(TodoListDO::getTitle, title);

        TodoListDO todoListDO = baseMapper.selectOne(queryWrapper);
        TodoListRespDTO result = new TodoListRespDTO();
        BeanUtil.copyProperties(todoListDO, result);

        return result;
    }

}
