package com.nexusy.virgo.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author lan
 * @since 2013-11-07
 */
public interface BaseDao<T, PK extends Serializable> {

    T get(PK id);

    T load(PK id);

    void save(T entity);

    void saveAll(List<T> entities);

    void update(T entity);

    void updateAll(List<T> entities);

    T merge(T entity);

    List<T> mergeAll(List<T> entities);

    void remove(T entity);
}
