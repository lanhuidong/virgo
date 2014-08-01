package com.nexusy.virgo.data.dao;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lan
 * @since 2013-11-15
 */
public class UserDaoTest extends DataAppTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindUserByUsername() {
        User user = userDao.findUserByUsername("lanhuidong");
        System.out.println(user.getUsername());
    }
}
