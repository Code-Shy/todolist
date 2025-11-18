package com.weijinchuan.todolist.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_todo")
public class TodoListDO {

    /* todolist ID */
    private Long id;

    /* 标题 */
    private String title;

    /* 描述 */
    private String description;

    /* 创建时间 */
    private Long createTime;

    /* 修改时间 */
    private Long updateTime;

    /* 删除时间 */
    private Long deleteTime;

    /* 分组id */
    private Integer cid;

}
