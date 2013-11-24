package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.dao.BillDao;
import com.nexusy.virgo.data.dao.UniversalDao;
import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.BillItem;
import com.nexusy.virgo.data.service.BillService;
import com.nexusy.virgo.data.vo.BillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private UniversalDao universalDao;

    @Override
    @Transactional(readOnly = false)
    public void saveBillItem(BillVo bill) {
        Bill newBill = billDao.findBillByDate(bill.getUserId(), bill.getDate());
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

    @Override
    public List<Bill> findBillsByDate(Long userId, Date from, Date to, Integer firstResult, Integer maxResults) {
        List<Long> ids = billDao.findBillsByDate(userId, from, to, firstResult, maxResults);
        List<Bill> bills;
        if (ids.isEmpty()) {
            bills = Collections.emptyList();
        } else {
            bills = billDao.findBillsWithBillItems(ids);
        }
        return bills;
    }

    @Override
    @Transactional(readOnly = false)
    public Integer deleteBillItem(Long userId, Long id) {
        BillItem item = universalDao.get(BillItem.class, id);
        if (item != null) {
            universalDao.remove(item);
            return 1;
        }
        return 0;
    }
}
