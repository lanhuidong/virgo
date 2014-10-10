package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.vo.BasicInfo;

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

    boolean modifyPassword(Long userId, String oldPassword, String newPassword);

    /**
     * 检查用户名是否存在，已存在返回true,否则返回false
     */
    boolean checkUsername(String username);

    void save(Long userId, BasicInfo info);
}
