package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.model.Bill;

import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-20
 */
public interface BillDao extends BaseDao<Bill, Long> {

    /**
     * 根据日期查找账单
     */
    Bill findBillByDate(Long userId, Date date);

    /**
     * 根据日期范围查询账单Id
     */
    List<Long> findBillsByDate(Long userId, Date from, Date to, Integer firstResult, Integer maxResults);

    /**
     * 查询并返回带明细的账单
     */
    List<Bill> findBillsWithBillItems(List<Long> ids);

}
