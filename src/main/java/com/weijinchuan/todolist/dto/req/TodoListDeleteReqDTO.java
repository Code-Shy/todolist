package com.weijinchuan.todolist.dto.req;

import lombok.Data;

@Data
public class TodoListDeleteReqDTO {

    private String title;

    private String description;

    private Integer cid;
}
