package com.nexusy.virgo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lan
 * @since 2013-11-16
 */
@Controller
public class UserController {

    @RequestMapping("/{username}")
    public ModelAndView home(@PathVariable String username) {
        return new ModelAndView("/profile/home");
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("/profile/home");
    }
}
