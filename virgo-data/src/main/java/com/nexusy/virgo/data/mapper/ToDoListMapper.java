package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.TodoList;

import java.util.List;
import java.util.Map;

/**
 * @author lan
 * @since 2014-08-05
 */
public interface TodoListMapper {

    void insertTodoList(TodoList todoList);

    List<TodoList> selectTodoListByDay(Map<String, Object> params);

    List<TodoList> selectTodoListByDateRange(Map<String, Object> params);

    Integer updateFinished(Map<String, Object> params);

    TodoList selectTodoList(Long id);
}
