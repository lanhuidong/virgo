package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.model.User;

/**
 * @author lan
 * @since 2013-11-15
 */
public interface UserService {

    /**
     * 保存用户并对密码加密
     */
    void save(User user);
}
