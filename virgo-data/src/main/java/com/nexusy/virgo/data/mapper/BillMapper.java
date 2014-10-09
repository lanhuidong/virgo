package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.Bill;
import com.nexusy.virgo.data.model.BillItem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author lan
 * @since 2014-10-08
 */
public interface BillMapper {

    Integer saveBill(Bill bill);

    Integer saveBillItem(BillItem billItem);

    Bill findBillByDate(@Param("userId") Long userId, @Param("date") Date date);

    List<Bill> findBillsWithBillItems(@Param("userId") Long userId, @Param("from") Date from, @Param("to") Date to);

    Integer deleteBillItem(@Param("userId") Long userId, @Param("id") Long id);
}
