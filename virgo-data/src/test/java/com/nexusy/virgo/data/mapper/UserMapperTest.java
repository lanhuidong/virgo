package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.DataAppTest;
import com.nexusy.virgo.data.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * @author lan
 * @since 2014-10-10
 */
public class UserMapperTest extends DataAppTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("testName");
        user.setPassword("testPassword");
        user.setEmail("test@nexusy.com");
        user.setPhone("18888888888");
        user.setLastLogin(new Date());
        user.setSignTime(new Date());
        userMapper.save(user);
        System.out.println(user.getId());
    }

    @Test
    public void testGet(){
        User user = userMapper.get(1L);
        System.out.println(user.getUsername());
    }

    @Test
    public void testFindUserByUsername(){
        User user = userMapper.findUserByUsername("lanhuidong");
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateLoginTime(){
        int result = userMapper.updateLoginTime(1L, new Date());
        System.out.println(result);
    }

    @Test
    public void testUpdatePassword(){
        int result = userMapper.updatePassword(1L, "xxx");
        System.out.println(result);
    }

    @Test
    public void testUpdateBasicInfo(){
        int result = userMapper.updateBasicInfo(1L, "xxx", "ooo");
        System.out.println(result);
    }
}
