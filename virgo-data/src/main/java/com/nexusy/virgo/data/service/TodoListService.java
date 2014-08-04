package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.model.TodoList;

import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2014-08-01
 */
public interface TodoListService {

    void save(TodoList todoList);

    List<TodoList> findTodoListByDay(Long userId, Date day);

    List<TodoList> findTodoListByDateRange(Long userId, Date from, Date to);

    Integer finish(Long userId, Long id);
}
