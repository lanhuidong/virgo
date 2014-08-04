package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.TodoList;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-01
 */
public interface TodoListMapper {

    void insertTodoList(Long id, Date created, String content, Date remindAt, Date finished);

    TodoList selectTodoList(Long id);
}
