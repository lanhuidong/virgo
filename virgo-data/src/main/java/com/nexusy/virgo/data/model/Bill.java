package com.nexusy.virgo.data.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 账单
 *
 * @author lan
 * @since 2013-11-15
 */
@Entity
@Table(name = "bills")
public class Bill {

    /**
     * 账单ID，由数据库自动生成
     */
    private Long id;

    /**
     * 日期，格式yyyy-MM-dd，由应用程序生成
     */
    private Date date;

    /**
     * 用户ID
     */
    private Long userId;

    private List<BillItem> items;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(nullable = false, updatable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @OneToMany(mappedBy = "bill", orphanRemoval = true)
    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }
}
