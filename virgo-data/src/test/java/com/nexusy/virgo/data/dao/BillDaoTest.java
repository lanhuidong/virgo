package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.util.VirgoDateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-27
 */
public class BillDaoTest extends DataAppTest {

    @Autowired
    private BillDao billDao;

    @Test
    public void testFindBillByDate() {
        billDao.findBillByDate(1L, VirgoDateUtils.getDate());
    }

    @Test
    public void testFindBillsWithBillItems() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        billDao.findBillsWithBillItems(ids);
    }

    @Test
    public void testFindBillsByDate() {
        billDao.findBillsByDate(1L, VirgoDateUtils.getDate(), VirgoDateUtils.getDate(), 0, 10);
    }

}
