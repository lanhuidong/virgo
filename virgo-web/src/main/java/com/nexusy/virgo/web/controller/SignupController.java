package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.web.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * This controller is used for signup.
 *
 * @author lan
 * @since 2013-11-15
 */
@Controller
public class SignupController {

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@Valid User user, BindingResult result) {
        return null;
    }
}
