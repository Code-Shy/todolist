package com.weijinchuan.todolist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weijinchuan.todolist.dao.entity.TodoListDO;
import com.weijinchuan.todolist.dto.req.TodoListAddReqDTO;
import com.weijinchuan.todolist.dto.req.TodoListDeleteReqDTO;
import com.weijinchuan.todolist.dto.req.TodoListUpdateReqDTO;
import com.weijinchuan.todolist.dto.resp.TodoListDeleteRespDTO;
import com.weijinchuan.todolist.dto.resp.TodoListRespDTO;
import com.weijinchuan.todolist.dto.resp.TodoListUpdateRespDTO;

public interface TodoListService extends IService<TodoListDO> {

    void addTodoList(TodoListAddReqDTO requestParam);

    void updateTodoList(TodoListUpdateReqDTO requestParam);

    void deleteTodoList(TodoListDeleteReqDTO requestParam);

    TodoListRespDTO getTodoListByTitle(String title);
}
