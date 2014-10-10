package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.BillItem;
import com.nexusy.virgo.data.model.BillItemType;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2014-10-08
 */
public class BillMapperTest extends DataAppTest {

    @Autowired
    private BillMapper billMapper;

    @Test
    public void testSaveBill() {
        Bill bill = new Bill();
        bill.setUserId(0L);
        bill.setDate(new Date());
        billMapper.saveBill(bill);
        Assert.notNull(bill.getId());
    }

    @Test
    public void testSaveBillItem() {
        BillItem billItem = new BillItem();
        billItem.setItem("test");
        billItem.setMoney(100d);
        billItem.setRemark("");
        billItem.setType(BillItemType.INCOME);
        billItem.setBillId(1L);
        billMapper.saveBillItem(billItem);
    }

    @Test
    public void testFindBillByDate() {
        Bill bill = billMapper.findBillByDate(1L, new Date());
        if (bill != null) {
            System.out.println(bill.getId());
        }
    }

    @Test
    public void testFindBillsWithBillItems() {
        try {
            Date from = DateUtils.parseDate("2013-11-22", "yyyy-MM-dd");
            Date to = DateUtils.parseDate("2013-11-29", "yyyy-MM-dd");
            List<Bill> bills = billMapper.findBillsWithBillItems(1L, from, to);
            System.out.println(bills.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteBillItem() {
        billMapper.deleteBillItem(0L, 0L);
    }
}
