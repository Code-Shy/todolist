package com.weijinchuan.todolist.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDO {

    /* 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /* 删除标识 */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
