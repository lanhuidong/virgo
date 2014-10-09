package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.mapper.TodoMapper;
import com.nexusy.virgo.data.model.Todo;
import com.nexusy.virgo.data.service.TodoService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lan
 * @since 2014-08-01
 */
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public void save(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    @Override
    public List<Todo> findUnfinishedTodos(Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Date day = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE), 7);
        params.put("day", day);
        return todoMapper.selectUnfinishedTodo(params);
    }

    @Override
    public List<Todo> findFinishedTodos(Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Date end = new Date();
        Date start = DateUtils.addMonths(end, -1);
        params.put("start", start);
        params.put("end", end);
        return todoMapper.selectFinishedTodo(params);
    }

    @Override
    public int finishTodo(Long userId, Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("id", id);
        params.put("day", new Date());
        return todoMapper.finishTodo(params);
    }

}
