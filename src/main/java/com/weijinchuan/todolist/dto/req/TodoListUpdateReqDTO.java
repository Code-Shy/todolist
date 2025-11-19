package com.weijinchuan.todolist.dto.req;

import lombok.Data;

@Data
public class TodoListUpdateReqDTO {

    private String title;

    private String description;

    private Integer cid;
}
