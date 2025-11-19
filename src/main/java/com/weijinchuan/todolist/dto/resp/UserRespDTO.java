package com.weijinchuan.todolist.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.weijinchuan.todolist.common.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

@Data
public class UserRespDTO {

    /* 用户ID */
    private Long id;

    /* 用户名 */
    private String username;

    /* 用户邮箱 */
    private String email;

    /* 用户手机号 */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;
}
