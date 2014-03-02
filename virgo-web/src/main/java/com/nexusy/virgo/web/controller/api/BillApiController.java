package com.nexusy.virgo.web.controller.api;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller is used for bill.
 *
 * @author lan
 * @since 2013-11-18
 */
@Controller
@RequestMapping("/api/bill")
public class BillApiController {

    @Autowired
    private BillService billService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Bill> query(DateRange range, Integer pageNo, Integer pageSize) {
        Page page = new Page(pageNo, pageSize);
        User user = VirgoSecurityContext.getCurrentUser();
        return billService.findBillsByDate(user.getId(), range.getFrom(), range.getTo(), page.getFirstResult(), page.getPageSize());
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

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Boolean deleteBill(@PathVariable Long id) {
        User user = VirgoSecurityContext.getCurrentUser();
        Integer result = billService.deleteBillItem(user.getId(), id);
        return result.equals(1);
    }

}