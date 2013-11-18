package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.web.bean.Bill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * This controller is used for bill.
 *
 * @author lan
 * @since 2013-11-18
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("/bill/index");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@Valid Bill bill) {
        return new ModelAndView();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        return new ModelAndView();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid Bill bill) {
        return new ModelAndView();
    }

    @RequestMapping("/delete/{id}")
    public Boolean deleteBill(@PathVariable Long id) {
        return true;
    }

}
