package com.nexusy.virgo.data.service.impl;

import com.nexusy.virgo.data.dao.UserDao;
import com.nexusy.virgo.data.model.User;
import com.nexusy.virgo.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lan
 * @since 2013-11-15
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.merge(user);
    }

    @Override
    public boolean chechUsername(String username) {
        return userDao.findUserByUsername(username) != null;
    }
}
