package com.weijinchuan.todolist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weijinchuan.todolist.dao.entity.UserDO;
import com.weijinchuan.todolist.dto.req.UserLoginReqDTO;
import com.weijinchuan.todolist.dto.req.UserRegisterReqDTO;
import com.weijinchuan.todolist.dto.resp.UserLoginRespDTO;
import com.weijinchuan.todolist.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    boolean hasUserName(String username);

    void register(UserRegisterReqDTO requestParam);

    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    UserRespDTO getUserByUsername(String username);
}
