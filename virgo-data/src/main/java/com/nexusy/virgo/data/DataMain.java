package com.nexusy.virgo.data;

import com.nexusy.virgo.data.config.DataSourceConfig;
import com.nexusy.virgo.data.config.MybatisConfig;
import com.nexusy.virgo.data.model.TodoList;
import com.nexusy.virgo.data.service.TodoListService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * @author lan
 * @since 2014-08-01
 */
public class DataMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class, MybatisConfig.class);
        TodoListService todoListService = ctx.getBean(TodoListService.class);
        TodoList toDoList =new TodoList();
        toDoList.setContent("service");
        toDoList.setCreated(new Date());
        todoListService.save(toDoList);
    }
}
