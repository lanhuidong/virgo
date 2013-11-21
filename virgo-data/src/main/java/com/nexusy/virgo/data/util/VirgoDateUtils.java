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

}