package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.TodoList;
import org.springframework.stereotype.Repository;

/**
 * @author lan
 * @since 2014-08-05
 */
@Repository
public interface TodoListMapper {

    void save(TodoList todoList);

    TodoList selectTodoList(Long id);
}
