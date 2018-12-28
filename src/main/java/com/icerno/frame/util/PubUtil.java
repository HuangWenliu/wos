package com.icerno.frame.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 公共工具类
 *
 * @author HWL
 */
public class PubUtil {

    /**
     * 获取32位UUID
     *
     * @return 返回UUID
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "").toUpperCase();
    }

    /**
     * 检测字符串是否为空 (null,"","null")
     *
     * @param str 检测字符
     * @return 检测结果
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    /**
     * 获取当前日期的字符串形式 (yyyy-MM-dd HH:mm:ss)
     *
     * @return 日期(字符串)
     */
    public static String getDateTime() {
        return date2Str(new Date());
    }

    public static String date2Str(Date date) {
        return date2Str(date, PubConstants.DATE_PATTERN_YMDHMS);
    }

    public static String date2Str(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
