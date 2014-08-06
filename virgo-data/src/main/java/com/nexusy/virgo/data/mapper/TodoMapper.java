package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.Todo;

/**
 * @author lan
 * @since 2014-08-06
 */
public interface TodoMapper {

    void insertTodo(Todo todo);

    Todo selectTodo(Long id);
}
