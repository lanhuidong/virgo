package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.model.Todo;
import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.TodoService;
import com.nexusy.virgo.data.vo.TodoVo;
import com.nexusy.virgo.web.security.VirgoSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author lan
 * @since 2014-08-05
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/todo/index");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/todo/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Boolean add(@Valid TodoVo todo, BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }
        User user = VirgoSecurityContext.getCurrentUser();
        Todo todoList = new Todo();
        todoList.setCreated(new Date());
        todoList.setContent(todo.getContent());
        todoList.setRemindAt(todo.getRemindAt());
        todoList.setRepeatType(todo.getRepeatType());
        todoList.setUserId(user.getId());
        todoService.insertTodo(todoList);
        return true;
    }
}
