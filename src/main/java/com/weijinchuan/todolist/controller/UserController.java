package com.weijinchuan.todolist.controller;

import com.weijinchuan.todolist.common.convention.result.Result;
import com.weijinchuan.todolist.common.convention.result.Results;
import com.weijinchuan.todolist.dto.req.UserLoginReqDTO;
import com.weijinchuan.todolist.dto.req.UserRegisterReqDTO;
import com.weijinchuan.todolist.dto.resp.UserLoginRespDTO;
import com.weijinchuan.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/todolist/actual/v1/user/register")
    public Result<Void> register(@RequestParam UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }


    @PostMapping("/api/todolist/actual/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }
}
