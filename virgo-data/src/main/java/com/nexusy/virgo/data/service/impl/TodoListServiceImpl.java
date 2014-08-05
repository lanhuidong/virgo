package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.mapper.TodoListMapper;
import com.nexusy.virgo.data.model.TodoList;
import com.nexusy.virgo.data.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lan
 * @since 2014-08-01
 */
@Service
@Transactional("transactionManager2")
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListMapper todoListMapper;

    @Override
    public void save(TodoList todoList) {
        todoListMapper.save(todoList);
    }

}
