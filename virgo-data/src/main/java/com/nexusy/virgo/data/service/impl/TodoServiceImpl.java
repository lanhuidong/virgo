package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.mapper.TodoMapper;
import com.nexusy.virgo.data.model.Todo;
import com.nexusy.virgo.data.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lan
 * @since 2014-08-01
 */
@Service
@Transactional("transactionManager2")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public void insertTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

}
