package com.nexusy.virgo.data.vo;

import com.nexusy.virgo.data.model.BillItemType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lan
 * @since 2013-11-18
 */
public class BillVo {

    private Date date;
    private String item;
    private Double money;
    private String remark;
    private BillItemType type;
    private Long userId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotBlank
    @Length(max = 255)
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @NotNull
    @Min(0)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Length(max = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BillItemType getType() {
        return type;
    }

    public void setType(BillItemType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
