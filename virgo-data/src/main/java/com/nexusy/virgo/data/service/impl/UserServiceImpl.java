package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public User get(Long id) {
        return userMapper.get(id);
    }

    @Override
    public void updateLoginTime(Long userId, Date time) {
        userMapper.updateLoginTime(userId, time);
    }

    @Override
    public void save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.save(user);
    }

    @Override
    public boolean modifyPassword(Long userId, String oldPassword, String newPassword) {
        User user = userMapper.get(userId);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            userMapper.updatePassword(user.getId(), passwordEncoder.encode(newPassword));
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUsername(String username) {
        return userMapper.findUserByUsername(username) != null;
    }

    @Override
    public void save(Long userId, BasicInfo info) {
        userMapper.updateBasicInfo(userId, info.getEmail(), info.getPhone());
    }
}
