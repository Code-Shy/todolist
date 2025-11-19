package com.weijinchuan.todolist.dto.resp;

import lombok.Data;

@Data
public class TodoListRespDTO {

    /* 标题 */
    private String title;

    /* 描述 */
    private String description;

    /* 分组 ID */
    private Integer cid;

}
