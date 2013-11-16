package com.nexusy.virgo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * This controller is used for custom error-page.
 *
 * @author lan
 * @since 2013-11-07
 */
@Controller
public class ErrorController {

    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("/403")
    public ModelAndView http403() {
        return new ModelAndView("/403");
    }

    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        ModelAndView mav;
        switch (statusCode) {
            case 404:
                mav = new ModelAndView("/404");
                break;
            case 500:
                mav = new ModelAndView("/500");
                Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
                logger.warn("a server internal error occur", e);
                break;
            default:
                mav = new ModelAndView("/index");
                break;
        }
        return mav;
    }

}