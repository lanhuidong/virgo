package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.UserService;
import com.nexusy.virgo.data.vo.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author lan
 * @since 2013-11-15
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userDao.findUserByUsername(username);
        return null;
    }

    @Override
    public User get(Long id) {
//        return userDao.get(id);
        return null;
    }

    @Override
    public void updateLoginTime(Long userId, Date time) {
//        User user = userDao.load(userId);
//        user.setLastLogin(time);
//        userDao.update(user);
    }

    @Override
    public void save(User user) {
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userDao.merge(user);
    }

    @Override
    public boolean modifyPassword(Long userId, String oldPassword, String newPassword) {
//        User user = userDao.get(userId);
//        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userDao.update(user);
//        } else {
//            return false;
//        }
        return true;
    }

    @Override
    public boolean chechUsername(String username) {
//        return userDao.findUserByUsername(username) != null;
        return false;
    }

    @Override
    public void save(Long userId, BasicInfo info) {
//        User user = userDao.get(userId);
//        if (user != null) {
//            user.setEmail(info.getEmail());
//            user.setPhone(info.getPhone());
//        }
//        userDao.update(user);
    }
}
