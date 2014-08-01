package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.config.DataSourceConfig;
import com.nexusy.virgo.data.config.MybatisConfig;
import com.nexusy.virgo.data.model.ToDoList;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, MybatisConfig.class})
@Transactional
public class ToDoListDaoTest {

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
