package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.model.Todo;

import java.util.List;

/**
 * @author lan
 * @since 2014-08-01
 */
public interface TodoService {

    void save(Todo todo);

    List<Todo> findUnfinishedTodos(Long userId);

    List<Todo> findFinishedTodos(Long userId);

    int finishTodo(Long userId, Long id);

}
