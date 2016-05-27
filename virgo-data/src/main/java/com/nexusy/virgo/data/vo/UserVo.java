package com.nexusy.virgo.data.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author lan
 * @since 2013-11-15
 */
public class UserVo {

    private String j_username;
    private String j_password;

    @NotBlank
    @Length(min = 4, max = 20)
    public String getJ_username() {
        return j_username;
    }

    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    @NotBlank
    @Length(min = 6, max = 20)
    public String getJ_password() {
        return j_password;
    }

    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

}
