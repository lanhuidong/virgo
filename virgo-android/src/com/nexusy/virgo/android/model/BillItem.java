package com.nexusy.virgo.android.model;

import java.io.Serializable;

/**
 * @author lan
 * @since 2014-3-2
 */
public class BillItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Bill bill;
    private Double money;
    private String item;
    private BillItemType type;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BillItemType getType() {
        return type;
    }

    public void setType(BillItemType type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
