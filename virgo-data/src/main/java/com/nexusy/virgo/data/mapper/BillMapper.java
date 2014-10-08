package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.BillItem;
import org.apache.ibatis.annotations.Param;

/**
 * @author lan
 * @since 2014-10-08
 */
public interface BillMapper {

    Integer saveBill(Bill bill);

    Integer saveBillItem(BillItem billItem);

    Integer deleteBillItem(@Param("userId") Long userId, @Param("id") Long id);
}
