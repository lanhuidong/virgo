package com.nexusy.virgo.data.model;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * 账单条目
 *
 * @author lan
 * @since 2013-11-15
 */
@Entity
@Table(name = "bill_items")
public class BillItem {

    /**
     * 账单条目ID，由数据库自动生成
     */
    private Long id;

    /**
     * 账单ID
     */
    private Bill bill;

    /**
     * 金额
     */
    private Double money;

    /**
     * 名目
     */
    private String item;

    /**
     * 条目类型，支出或者收入
     */
    private BillItemType type;

    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    @ForeignKey(name = "FK_bill_id")
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

    @Enumerated(EnumType.STRING)
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
