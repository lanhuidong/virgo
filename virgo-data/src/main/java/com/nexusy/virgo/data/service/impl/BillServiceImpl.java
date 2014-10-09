package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.mapper.BillMapper;
import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.BillItem;
import com.nexusy.virgo.data.service.BillService;
import com.nexusy.virgo.data.vo.BillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-20
 */
@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public void saveBillItem(BillVo bill) {
        Bill newBill = billMapper.findBillByDate(bill.getUserId(), bill.getDate());
        if (newBill == null) {
            newBill = new Bill();
            newBill.setDate(bill.getDate());
            newBill.setUserId(bill.getUserId());
            billMapper.saveBill(newBill);
        }

        BillItem item = new BillItem();
        item.setItem(bill.getItem());
        item.setMoney(bill.getMoney());
        item.setType(bill.getType());
        item.setRemark(bill.getRemark());
        item.setBillId(newBill.getId());
        billMapper.saveBillItem(item);
    }

    @Override
    public List<Bill> findBillsByDate(Long userId, Date from, Date to, Integer firstResult, Integer maxResults) {
        return billMapper.findBillsWithBillItems(userId, from, to);
    }

    @Override
    public Integer deleteBillItem(Long userId, Long id) {
        return billMapper.deleteBillItem(userId, id);
    }
}
