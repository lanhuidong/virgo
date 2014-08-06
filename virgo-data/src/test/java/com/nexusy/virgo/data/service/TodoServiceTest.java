package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.RepeatType;
import com.nexusy.virgo.data.model.Todo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-04
 */
public class TodoServiceTest extends DataAppTest {

    @Autowired
    private TodoService todoListService;

    @Test
    public void testSave() {
        Todo todoList = new Todo();
        todoList.setCreated(new Date());
        todoList.setContent("测试");
        todoList.setRemindAt(new Date());
        todoList.setRepeatType(RepeatType.NO_REPEAT);
        todoList.setUserId(1L);
        todoListService.save(todoList);
    }

}
