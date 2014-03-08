package com.nexusy.virgo.android.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author lan
 * @since 2014-3-2
 */
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date date;
    private Long userId;
    private List<BillItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

}
