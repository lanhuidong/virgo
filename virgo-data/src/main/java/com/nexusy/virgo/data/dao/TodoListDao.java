package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.model.TodoList;

/**
 * @author lan
 * @since 2014-08-05
 */
public interface TodoListDao {

    void save(TodoList todoList);

    TodoList findTodoList(Long id);
}
