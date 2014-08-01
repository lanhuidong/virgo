package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.dao.ToDoListDao;
import com.nexusy.virgo.data.model.ToDoList;
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
    private ToDoListDao toDoListDao;

    @Override
    public void save(ToDoList toDoList) {
        toDoListDao.save(toDoList);
    }
}
