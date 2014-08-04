package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.LoopType;
import com.nexusy.virgo.data.model.TodoList;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-01
 */
public class TodoListDaoTest extends DataAppTest {

    @Autowired
    private TodoListDao todoListDao;

    @Test
    @Rollback(false)
    public void testSave() {
        TodoList todoList = new TodoList();
        todoList.setContent("test");
        todoList.setCreated(new Date());
        todoList.setRemindAt(new Date());
        todoList.setFinished(new Date());
        todoList.setLoopType(LoopType.MONTH);
        todoListDao.save(todoList);
    }

    @Test
    public void testGet() {
        TodoList todoList = todoListDao.get(1L);
        System.out.println(todoList.getContent());
    }
}
