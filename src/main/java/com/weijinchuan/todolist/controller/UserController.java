package com.weijinchuan.todolist.controller;

import com.weijinchuan.todolist.common.convention.result.Result;
import com.weijinchuan.todolist.common.convention.result.Results;
import com.weijinchuan.todolist.dto.req.UserRegisterReqDTO;
import com.weijinchuan.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/todolist/actual/v1/user")
    public Result<Void> register(@RequestParam UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }


}
