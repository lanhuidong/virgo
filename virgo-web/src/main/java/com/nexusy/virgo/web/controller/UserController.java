package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller is used for user home-page.
 * @author lan
 * @since 2013-11-16
 */
@Controller
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public ModelAndView home(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/u/index");
        User user = userService.get(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("/u/index");
    }
}
