package com.nexusy.virgo.data.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author lan
 * @since 2013-11-15
 */
public class UserVo {

    private String username;
    private String password;

    @NotBlank
    @Length(min = 4, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Length(min = 6, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
