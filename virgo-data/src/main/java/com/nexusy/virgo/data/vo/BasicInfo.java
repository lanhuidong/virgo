package com.nexusy.virgo.data.vo;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

/**
 * @author lan
 * @since 2013-12-10
 */
public class BasicInfo {

    private String email;
    private String phone;

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
