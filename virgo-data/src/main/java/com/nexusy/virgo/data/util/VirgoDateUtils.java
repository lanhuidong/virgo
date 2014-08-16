package com.nexusy.virgo.data.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lan
 * @since 2013-11-20
 */
public class VirgoDateUtils {

    /**
     * 返回只包含日期部分的系统当前时间，yyyy-MM-dd
     */
    public static Date getDate() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    /**
     * 返回每月的第一天
     */
    public static Date getFirstDay() {
        return DateUtils.truncate(new Date(), Calendar.MONTH);
    }

    /**
     * 将java.util.Date类型转换为yyyy-MM-dd形式的字符串
     */
    public static String getDate2String(Date date) {
        return String.format("%1$tF", date);
    }

}
