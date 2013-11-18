package com.nexusy.virgo.web.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果注册成功时自动登录成功，则进入用户主页，否则返回0
 *
 * @author lan
 * @since 2013-11-16
 */
@Component
public class VirgoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String fromSignup = request.getParameter("fromSignup");
        if (fromSignup != null) {
            String username = authentication.getName();
            response.sendRedirect("/" + username);
        } else {
            response.setHeader("Content-Type", "application/json");
            response.getWriter().write("0");
        }
    }

}
