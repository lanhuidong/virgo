package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.Todo;

import java.util.List;
import java.util.Map;

/**
 * @author lan
 * @since 2014-08-06
 */
public interface TodoMapper {

    void insertTodo(Todo todo);

    List<Todo> selectUnfinishedTodo(Map<String, Object> params);

    List<Todo> selectFinishedTodo(Map<String, Object> params);

    int finishTodo(Map<String, Object> params);
}
