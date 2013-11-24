package com.nexusy.virgo.data.dao;

import java.io.Serializable;

/**
 * @author lan
 * @since 2012-10-16
 */
public interface UniversalDao {

    <T> T get(Class<T> clazz, Serializable id);

    void remove(Object entity);

}
