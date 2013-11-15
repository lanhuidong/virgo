package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.DataAppTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lan
 * @since 2013-11-15
 */
public class UserDaoTests extends DataAppTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindUserByUsername() {
        userDao.findUserByUsername("lan");
    }
}
