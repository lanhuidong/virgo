package com.nexusy.virgo.data.service;

import java.io.Serializable;

/**
 * @author lan
 * @since 2013-12-17
 */
public interface BaseService<T, PK extends Serializable> {

    T get(PK id);

}
