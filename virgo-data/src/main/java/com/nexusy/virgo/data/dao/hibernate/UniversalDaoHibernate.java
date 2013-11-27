package com.nexusy.virgo.data.dao.hibernate;

import com.nexusy.virgo.data.dao.UniversalDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author lan
 * @since 2012-10-16
 */
@Repository
public class UniversalDaoHibernate implements UniversalDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz, Serializable id) {
        return (T) getSession().load(clazz, id);
    }

    @Override
    public void remove(Object entity) {
        getSession().delete(entity);
    }

}
