package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.mapper.TodoListMapper;
import com.nexusy.virgo.data.model.TodoList;
import com.nexusy.virgo.data.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        todoListMapper.insertTodoList(todoList);
    }

    @Override
    public List<TodoList> findTodoListByDay(Long userId, Date day) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("day", day);
        return todoListMapper.selectTodoListByDay(params);
    }

    @Override
    public List<TodoList> findTodoListByDateRange(Long userId, Date from, Date to) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("from", from);
        params.put("to", to);
        return todoListMapper.selectTodoListByDateRange(params);
    }

    @Override
    public Integer finish(Long userId, Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("id", id);
        params.put("finished", new Date());
        return todoListMapper.updateFinished(params);
    }
}
