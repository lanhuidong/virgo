package com.nexusy.virgo.data.service;

import com.nexusy.virgo.data.DataAppTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lan
 * @since 2013-11-15
 */
public class UserServiceTest extends DataAppTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncode() {
        String rawPassword = "472897576";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        Assert.assertTrue(passwordEncoder.matches("472897576", "194775504236e722546fc3a5dbd04c80e77100ebc425ddd871b1faed432658eb339b7b3fa0cde748"));
    }

}
