package com.nexusy.virgo.data.dao.hibernate;

import com.nexusy.virgo.data.dao.BillDao;
import com.nexusy.virgo.data.model.Bill;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> findBillsByDate(Long userId, Date from, Date to, Integer firstResult, Integer maxResults) {
        Criteria c = getSession().createCriteria(Bill.class);
        c.add(Restrictions.eq("userId", userId)).add(Restrictions.ge("date", from)).add(Restrictions.le("date", to));
        c.setFirstResult(firstResult).setMaxResults(maxResults);
        c.setProjection(Projections.id());
        return c.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> findBillsWithBillItems(List<Long> ids) {
        Criteria c = getSession().createCriteria(Bill.class).createAlias("items", "i", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.in("id", ids));
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return c.list();
    }
}
