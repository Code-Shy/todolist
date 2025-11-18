package com.weijinchuan.todolist.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class UserDO {

    /* 用户ID */
    private Long id;

    /* 用户名 */
    private String username;

    /* 用户邮箱 */
    private String email;

    /* 用户手机号 */
    private String phone;

    /* 用户密码 */
    private String password;

    /* 创建时间戳 */
    private Long createTime;

    /* 删除标识 */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

}
