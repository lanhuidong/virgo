package com.nexusy.virgo.data.service;


import com.nexusy.virgo.data.vo.BillVo;

/**
 * @author lan
 * @since 2013-11-20
 */
public interface BillService {

    /**
     * 保存账单明细
     */
    void saveBillItem(BillVo bill);
}