package com.nexusy.virgo.data.service;


import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.vo.BillVo;

import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-20
 */
public interface BillService {

    /**
     * 保存账单明细
     */
    void saveBillItem(BillVo bill);

    /**
     * 根据时间范围查找账单，返回结果中包含明细
     */
    List<Bill> findBillsByDate(Long userId, Date from, Date to, Integer firstResult, Integer maxResults);
}
