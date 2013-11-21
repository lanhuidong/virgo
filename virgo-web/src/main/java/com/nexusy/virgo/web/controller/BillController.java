package com.nexusy.virgo.web.controller;

import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.BillService;
import com.nexusy.virgo.data.vo.BillVo;
import com.nexusy.virgo.web.security.VirgoSecurityContext;
import com.nexusy.virgo.web.util.DateRange;
import com.nexusy.virgo.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
    public ModelAndView index(DateRange range, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "30") Integer pageSize) {
        ModelAndView mav = new ModelAndView("/bill/index");
        Page page = new Page(pageNo, pageSize);
        User user = VirgoSecurityContext.getCurrentUser();
        List<Bill> bills = billService.findBillsByDate(user.getId(), range.getFrom(), range.getTo(), page.getFirstResult(), page.getPageSize());
        mav.addObject("bills", bills);
        mav.addObject("page", page);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/bill/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(@Valid BillVo bill, BindingResult result) {
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
    public ModelAndView update(@Valid BillVo bill) {
        return new ModelAndView();
    }

    @RequestMapping("/delete/{id}")
    public Boolean deleteBill(@PathVariable Long id) {
        return true;
    }

}
