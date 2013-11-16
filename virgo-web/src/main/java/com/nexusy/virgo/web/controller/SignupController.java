package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.service.UserService;
import com.nexusy.virgo.web.bean.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

/**
 * This controller is used for signup.
 *
 * @author lan
 * @since 2013-11-15
 */
@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        return new ModelAndView("/signup");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {

        }
        com.nexusy.virgo.data.model.User newUser = new com.nexusy.virgo.data.model.User();
        BeanUtils.copyProperties(user, newUser);
        Date date = new Date();
        newUser.setSignTime(date);
        newUser.setLastLogin(date);
        userService.save(newUser);
        return null;
    }
}
