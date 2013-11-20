package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.model.Bill;

import java.util.Date;

/**
 * @author lan
 * @since 2013-11-20
 */
public interface BillDao extends BaseDao<Bill, Long> {

    /**
     * 根据日期查找账单
     */
    Bill findBillByDate(Long userId, Date date);
}
