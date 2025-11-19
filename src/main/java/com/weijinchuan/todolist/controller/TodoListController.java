package com.weijinchuan.todolist.controller;

import com.weijinchuan.todolist.common.convention.result.Result;
import com.weijinchuan.todolist.common.convention.result.Results;
import com.weijinchuan.todolist.dto.req.TodoListAddReqDTO;
import com.weijinchuan.todolist.dto.req.TodoListDeleteReqDTO;
import com.weijinchuan.todolist.dto.req.TodoListUpdateReqDTO;
import com.weijinchuan.todolist.dto.resp.TodoListRespDTO;
import com.weijinchuan.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    @GetMapping("/api/todolist/actual/v1/todo/{title}")
    public Result<TodoListRespDTO> getTodoListByTitle(@PathVariable("title") String title) {
        return Results.success(todoListService.getTodoListByTitle(title));
    }

    @PostMapping("/api/todolist/actual/v1/todo/add")
    public Result<Void> addTodoList(@RequestParam TodoListAddReqDTO requestParam) {
        todoListService.addTodoList(requestParam);
        return Results.success();
    }

    @PostMapping("/api/todolist/actual/v1/todo/update")
    public Result<Void> updateTodoList(@RequestParam TodoListUpdateReqDTO requestParam) {
        todoListService.updateTodoList(requestParam);
        return Results.success();
    }

    @PostMapping("/api/todolist/actual/v1/todo/delete")
    public Result<Void> deleteTodoList(@RequestParam TodoListDeleteReqDTO requestParam) {
        todoListService.deleteTodoList(requestParam);
        return Results.success();
    }
}
