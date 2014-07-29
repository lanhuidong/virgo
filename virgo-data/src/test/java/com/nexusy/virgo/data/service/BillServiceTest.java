package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.DataAppTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lan
 * @since 2013-11-27
 */
public class BillServiceTest extends DataAppTest {

    @Autowired
    private BillService billService;

    @Test
    public void test(){
         billService.deleteBillItem(1L, 1L);
    }
}
