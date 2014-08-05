package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.RepeatType;
import com.nexusy.virgo.data.model.TodoList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-04
 */
public class TodoListTest extends DataAppTest {

    @Autowired
    private TodoListService todoListService;

    @Test
    public void testSave() {
        TodoList todoList = new TodoList();
        todoList.setCreated(new Date());
        todoList.setContent("ceshi");
        todoList.setRemindAt(new Date());
        todoList.setRepeatType(RepeatType.NO_REPEAT);
        todoList.setUserId(1L);
        todoListService.save(todoList);
    }

}
