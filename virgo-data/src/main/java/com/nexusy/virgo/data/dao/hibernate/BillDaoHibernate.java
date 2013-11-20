package com.nexusy.virgo.data.dao.hibernate;

import com.nexusy.virgo.data.dao.BillDao;
import com.nexusy.virgo.data.model.Bill;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lan
 * @since 2013-11-20
 */
@Component
public class BillDaoHibernate extends BaseDaoHibernate<Bill, Long> implements BillDao {

    public BillDaoHibernate() {
        super(Bill.class);
    }

    @Override
    public Bill findBillByDate(Long userId, Date date) {
        Query query = getSession().createQuery("from Bill b where b.userId=:userId and b.date=:date");
        query.setLong("userId", userId).setDate("date", date);
        return (Bill) query.uniqueResult();
    }
}
