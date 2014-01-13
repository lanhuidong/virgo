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

    @Pattern(regexp = "^((\\s*)|((\\+86)|(86))?((15)|(13)|(18))\\d{9})$")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
