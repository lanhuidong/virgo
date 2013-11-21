package com.nexusy.virgo.web.util;

import com.nexusy.virgo.data.util.VirgoDateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lan
 * @since 2013-11-21
 */
public class DateRange {

    private Date from;
    private Date to;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getFrom() {
        if (from == null) {
            from = VirgoDateUtils.getFirstDay();
        }
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getTo() {
        if (to == null) {
            to = VirgoDateUtils.getDate();
        }
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

}
