package com.nexusy.virgo.data.mapper;

import com.nexusy.virgo.data.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author lan
 * @since 2014-10-10
 */
public interface UserMapper {

    void save(User user);

    User get(Long id);

    User findUserByUsername(String username);

    int updateLoginTime(@Param("userId") Long userId, @Param("time") Date time);

    int updatePassword(@Param("userId") Long userId, @Param("password") String password);

    int updateBasicInfo(@Param("userId") Long userId, @Param("email") String email, @Param("phone") String phone);

}
