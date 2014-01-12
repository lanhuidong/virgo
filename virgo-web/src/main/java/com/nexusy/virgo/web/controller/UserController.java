package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.UserService;
import com.nexusy.virgo.data.vo.BasicInfo;
import com.nexusy.virgo.data.vo.PasswordVo;
import com.nexusy.virgo.web.security.VirgoSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * This controller is used for user home-page.
 *
 * @author lan
 * @since 2013-11-16
 */
@Controller
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/u/index");
        User user = VirgoSecurityContext.getCurrentUser();
        user = userService.get(user.getId());
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public boolean edit(@Valid BasicInfo info, BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }
        User user = VirgoSecurityContext.getCurrentUser();
        userService.save(user.getId(), info);
        return true;
    }

    @RequestMapping("/modify")
    @ResponseBody
    public boolean modify(@Valid PasswordVo vo, BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }
        User user = VirgoSecurityContext.getCurrentUser();
        return userService.modifyPassword(user.getId(), vo.getOldPassword(), vo.getNewPassword());
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("/u/index");
    }
}
