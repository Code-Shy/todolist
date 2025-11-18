package com.weijinchuan.todolist.dto.req;

import lombok.Data;

@Data
public class UserLoginReqDTO {

    /* 用户名 */
    private String username;

    /* 用户密码 */
    private String password;
}
