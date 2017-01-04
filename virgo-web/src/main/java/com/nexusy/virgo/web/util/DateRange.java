package com.nexusy.virgo.web.util;

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
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

}
