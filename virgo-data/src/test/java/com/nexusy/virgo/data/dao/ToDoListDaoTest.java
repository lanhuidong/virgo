package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.ToDoList;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-01
 */
public class ToDoListDaoTest extends DataAppTest {

    @Autowired
    private ToDoListDao toDoListDao;

    @Test
    @Ignore
    public void testSave() {
        ToDoList toDoList = new ToDoList();
        toDoList.setContent("test");
        toDoList.setCreated(new Date());
        toDoListDao.save(toDoList);
    }

    @Test
    @Ignore
    public void testGet() {
        ToDoList toDoList = toDoListDao.get(1L);
        System.out.println(toDoList.getContent());
    }
}
