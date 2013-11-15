package com.nexusy.virgo.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 *
 * @author lan
 * @since 2013-11-15
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * 用户ID，由数据库自动生成
     */
    private Long id;

    /**
     * 用户名，由4-20个字符组成
     */
    private String username;

    /**
     * 密码，用加密算法加密
     */
    private String password;

    /**
     * 电子邮箱，可不填
     */
    private String email;

    /**
     * 手机号，可不填
     */
    private String phone;

    /**
     * 注册时间，由应用程序生成
     */
    private Date signTime;

    /**
     * 最后一次登录时间，由应用程序生成
     */
    private Date lastLogin;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false)
    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    @Column(nullable = false)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
