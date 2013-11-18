package com.nexusy.virgo.web.interceptor;

import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.web.security.VirgoSecurityContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lan
 * @since 2013-11-15
 */
public class VirgoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            User user = VirgoSecurityContext.getCurrentUser();
            modelAndView.addObject("user", user);
        }
    }
}
