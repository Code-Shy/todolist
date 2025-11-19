package com.weijinchuan.todolist.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.weijinchuan.todolist.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_todo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDO extends BaseDO {

    /* todolist ID */
    private Long id;

    /* 标题 */
    private String title;

    /* 描述 */
    private String description;

    /* 修改时间 */
    private Long updateTime;

    /* 删除时间 */
    private Long deleteTime;

    /* 分组id */
    private Integer cid;

}
