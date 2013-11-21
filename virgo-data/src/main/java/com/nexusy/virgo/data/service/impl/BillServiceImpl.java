package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.dao.BillDao;
import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.BillItem;
import com.nexusy.virgo.data.service.BillService;
import com.nexusy.virgo.data.util.VirgoDateUtils;
import com.nexusy.virgo.data.vo.BillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-20
 */
@Service
@Transactional(readOnly = true)
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    @Transactional(readOnly = false)
    public void saveBillItem(BillVo bill) {
        Date date = VirgoDateUtils.getDate();
        Bill newBill = billDao.findBillByDate(bill.getUserId(), date);
        if (newBill == null) {
            newBill = new Bill();
            newBill.setDate(bill.getDate());
            newBill.setUserId(bill.getUserId());
        }

        List<BillItem> items = new ArrayList<>(1);

        BillItem item = new BillItem();
        item.setItem(bill.getItem());
        item.setMoney(bill.getMoney());
        item.setType(bill.getType());
        item.setRemark(bill.getRemark());
        item.setBill(newBill);

        items.add(item);

        newBill.setItems(items);

        billDao.merge(newBill);
    }
}
