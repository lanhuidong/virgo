package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.dao.BaseDao;
import com.nexusy.virgo.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author lan
 * @since 2013-12-17
 */
public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    private BaseDao<T, PK> baseDao;

    @Override
    public T get(PK id) {
        return baseDao.get(id);
    }
}
