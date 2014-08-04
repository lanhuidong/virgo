package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.model.TodoList;

/**
 * @author lan
 * @since 2014-08-01
 */
public interface TodoListDao {

    void save(TodoList toDoList);

    TodoList get(Long id);

}
