package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.bean.Bill;
import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.BillService;
import com.nexusy.virgo.web.security.VirgoSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private BillService billService;

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("/bill/index");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/bill/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(@Valid Bill bill, BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }
        User user = VirgoSecurityContext.getCurrentUser();
        bill.setUserId(user.getId());
        billService.saveBillItem(bill);
        return true;
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
