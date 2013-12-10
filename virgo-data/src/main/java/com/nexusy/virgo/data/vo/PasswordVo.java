package com.nexusy.virgo.data.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author lan
 * @since 2013-12-10
 */
public class PasswordVo {

    private String oldPassword;
    private String newPassword;

    @NotBlank
    @Length(min = 6, max = 20)
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotBlank
    @Length(min = 6, max = 20)
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
