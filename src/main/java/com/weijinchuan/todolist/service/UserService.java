package com.weijinchuan.todolist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weijinchuan.todolist.dao.entity.UserDO;
import com.weijinchuan.todolist.dto.req.UserRegisterReqDTO;

public interface UserService extends IService<UserDO> {

    boolean hasUserName(String username);

    void register(UserRegisterReqDTO requestParam);
}
