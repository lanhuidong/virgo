package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.model.User;

/**
 * @author lan
 * @since 2013-11-15
 */
public interface UserDao extends BaseDao<User, Long> {

    /**
     * 根据用户名查找用户
     */
    User findUserByUsername(String username);
}
