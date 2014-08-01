package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.ToDoList;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-01
 */
public interface ToDoListMapper {

    void insertTodoList(Long id, Date created, String content, Date todoTime, Date remindAt, Date finished);

    ToDoList selectTodoList(Long id);
}
