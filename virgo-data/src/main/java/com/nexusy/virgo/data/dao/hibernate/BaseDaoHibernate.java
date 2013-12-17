package com.nexusy.virgo.data.dao.hibernate;

import com.nexusy.virgo.data.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-07
 */
public abstract class BaseDaoHibernate<T, PK extends Serializable> implements BaseDao<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> entityClass;

    public BaseDaoHibernate(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        return (T) getSession().get(entityClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T load(PK id) {
        return (T) getSession().load(entityClass, id);
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void saveAll(List<T> entities) {
        Session session = getSession();
        for (T entity : entities) {
            session.save(entity);
        }
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void updateAll(List<T> entities) {
        Session session = getSession();
        for (T entity : entities) {
            session.update(entity);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T merge(T entity) {
        return (T) getSession().merge(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> mergeAll(List<T> entities) {
        List<T> results = new ArrayList<>(entities.size());
        Session session = getSession();
        for (T entity : entities) {
            results.add((T) session.merge(entity));
        }
        return results;
    }

    @Override
    public void remove(T entity) {
        getSession().delete(entity);
    }
}
