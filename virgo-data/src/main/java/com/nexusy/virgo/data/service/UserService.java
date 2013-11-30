package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.model.User;

import java.util.Date;

/**
 * @author lan
 * @since 2013-11-15
 */
public interface UserService {

    User get(Long id);

    void updateLoginTime(Long userId, Date time);

    /**
     * 保存用户并对密码加密
     */
    void save(User user);

    /**
     * 检查用户名是否存在，已存在返回true,否则返回false
     */
    boolean chechUsername(String username);
}
