package com.weijinchuan.todolist.dto.resp;

import lombok.Data;

@Data
public class UserRegisterRespDTO {

    /* 用户ID */
    private Long id;

    /* 用户名 */
    private String username;

    /* 用户邮箱 */
    private String email;

    /* 用户手机号 */
    private String phone;

}
