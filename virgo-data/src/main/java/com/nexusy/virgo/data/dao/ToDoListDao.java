package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.model.ToDoList;

/**
 * @author lan
 * @since 2014-08-01
 */
public interface ToDoListDao {

    void save(ToDoList toDoList);

    ToDoList get(Long id);

}
