package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.service.UserService;
import com.nexusy.virgo.data.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public int signup(@Valid User user, BindingResult result) {
        int code;
        if (result.hasErrors()) {
            code =  1;
        } else if(userService.chechUsername(user.getJ_username())){
            code =  2;
        } else {
            com.nexusy.virgo.data.model.User newUser = new com.nexusy.virgo.data.model.User();
            newUser.setUsername(user.getJ_username());
            newUser.setPassword(user.getJ_password());
            Date date = new Date();
            newUser.setSignTime(date);
            newUser.setLastLogin(date);
            userService.save(newUser);
            code = 0;
        }
        return code;
    }

    @RequestMapping("/signup/{username}")
    @ResponseBody
    public boolean checkUsername(@PathVariable String username){
        return userService.chechUsername(username);
    }
}
