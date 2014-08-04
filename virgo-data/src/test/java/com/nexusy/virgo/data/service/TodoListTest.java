package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.LoopType;
import com.nexusy.virgo.data.model.TodoList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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
        todoList.setContent("test content");
        todoList.setCreated(new Date());
        todoList.setLoopType(LoopType.WEEK);
        todoList.setUserId(1L);
        todoListService.save(todoList);
    }

    @Test
    public void testFindTodoListByDay() {
        List<TodoList> todoLists = todoListService.findTodoListByDay(1L, new Date());
        for (TodoList todoList : todoLists) {
            System.out.println(todoList.getContent());
        }
    }

    @Test
    public void testFinish(){
        todoListService.finish(1L, 2L);
    }
}
