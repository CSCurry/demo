package com.demo.framework.util;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author 30
 */
@SuppressWarnings("unused")
public class DateUtil {

    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY$MM$DD = "yyyy/MM/dd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY$MM$DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    public static final String HHMMSS = "HHmmss";
    public static final String HH_MM_SS = "HH:mm:ss";

    private static final SimpleDateFormat FORMAT_YYYY = new SimpleDateFormat(YYYY);
    private static final SimpleDateFormat FORMAT_YYYY_MM = new SimpleDateFormat(YYYY_MM);
    private static final SimpleDateFormat FORMAT_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);
    private static final SimpleDateFormat FORMAT_YYYY$MM$DD = new SimpleDateFormat(YYYY$MM$DD);
    private static final SimpleDateFormat FORMAT_YYYYMMDDHHMMSS = new SimpleDateFormat(YYYYMMDDHHMMSS);
    private static final SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    private static final SimpleDateFormat FORMAT_YYYY$MM$DD_HH_MM_SS = new SimpleDateFormat(YYYY$MM$DD_HH_MM_SS);
    private static final SimpleDateFormat FORMAT_HHMMSS = new SimpleDateFormat(HHMMSS);
    private static final SimpleDateFormat FORMAT_HH_MM_SS = new SimpleDateFormat(HH_MM_SS);

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

//    public static void main(String[] args) {
//        String s = formatDate(now(), YYYY_MM_DD_HH_MM_SS);
//        String s1 = getYesterdayTimeStr();
//        System.out.println(s);
//        System.out.println(s1);
//        System.out.println(diffTime(s, s1));
//        System.out.println(diffTime(now(), getYesterday()));
//        System.out.println(formatNow(YYYY$MM$DD_HH_MM_SS));
//        System.out.println(getTodayFirstSecond());
//        System.out.println(getTodayLastSecond());
//        System.out.println(FORMAT_YYYY_MM_DD_HH_MM_SS.format(getStartDayOfMonth()));
//        System.out.println(FORMAT_YYYY_MM_DD_HH_MM_SS.format(getLastDayOfMonth()));
//    }

    /**
     * 获取当前时间
     */
    public static Date now() {
        return new Date();
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 格式化 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//

    /**
     * 格式化当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatNow() {
        return formatDate(now(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化当前时间
     *
     * @param pattern 格式
     * @return 格式化后的日期时间字符串
     */
    public static String formatNow(String pattern) {
        return formatDate(now(), pattern);
    }

    /**
     * 格式化指定时间
     *
     * @param date Date对象
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(Date date) {
        return formatDate(now(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化指定时间
     *
     * @param date    Date对象
     * @param pattern 格式
     * @return 格式化后的日期时间字符串
     */
    public static String formatDate(Date date, String pattern) {
        switch (pattern) {
            case YYYY:
                return FORMAT_YYYY.format(date);
            case YYYY_MM:
                return FORMAT_YYYY_MM.format(date);
            case YYYY_MM_DD:
                return FORMAT_YYYY_MM_DD.format(date);
            case YYYY$MM$DD:
                return FORMAT_YYYY$MM$DD.format(date);
            case YYYYMMDDHHMMSS:
                return FORMAT_YYYYMMDDHHMMSS.format(date);
            case YYYY_MM_DD_HH_MM_SS:
                return FORMAT_YYYY_MM_DD_HH_MM_SS.format(date);
            case YYYY$MM$DD_HH_MM_SS:
                return FORMAT_YYYY$MM$DD_HH_MM_SS.format(date);
            case HHMMSS:
                return FORMAT_HHMMSS.format(date);
            case HH_MM_SS:
                return FORMAT_HH_MM_SS.format(date);
            default:
                return "";
        }
    }

    /**
     * 日期型字符串转化为Date
     */
    public static Date parseDate(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(obj.toString(), parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 判断 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//

    /**
     * 判断时间1是否在时间2之前
     *
     * @return 判断结果
     */
    public static boolean before(String time1, String time2) {
        try {
            Date dateTime1 = FORMAT_YYYY_MM_DD_HH_MM_SS.parse(time1);
            Date dateTime2 = FORMAT_YYYY_MM_DD_HH_MM_SS.parse(time2);
            return dateTime1.before(dateTime2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断时间1是否在时间2之后
     *
     * @return 判断结果
     */
    public static boolean after(String time1, String time2) {
        try {
            Date dateTime1 = FORMAT_YYYY_MM_DD_HH_MM_SS.parse(time1);
            Date dateTime2 = FORMAT_YYYY_MM_DD_HH_MM_SS.parse(time2);
            return dateTime1.after(dateTime2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 计算 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//

    /**
     * 计算时间差：time1 - time2
     *
     * @return s秒
     */
    public static int diffTime(String time1, String time2) {
        try {
            Date datetime1 = FORMAT_YYYY_MM_DD_HH_MM_SS.parse(time1);
            Date datetime2 = FORMAT_YYYY_MM_DD_HH_MM_SS.parse(time2);
            long millisecond = datetime1.getTime() - datetime2.getTime();
            return Integer.parseInt(String.valueOf(millisecond / 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算时间差：endDate - beginDate
     *
     * @return d天h小时m分钟s秒
     */
    public static String diffTime(Date endDate, Date beginDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - beginDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 获取 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//

    /**
     * 获取日期路径 即年/月/日 如2018/08/08
     */
    public static String getDatePath() {
        return formatDate(now(), YYYY$MM$DD);
    }

    /**
     * 获取服务器启动时间
     *
     * @return Date
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 获取今天的开始时间
     *
     * @return yyyy-MM-dd 00:00:00
     */
    public static String getTodayFirstSecond() {
        return getDayFirstSecond(now());
    }

    /**
     * 获取一天的开始时间
     *
     * @param date 指定date
     * @return yyyy-MM-dd 00:00:00
     */
    public static String getDayFirstSecond(Date date) {
        return FORMAT_YYYY_MM_DD.format(date) + " 00:00:00";
    }

    /**
     * 获取今天的结束时间
     *
     * @return yyyy-MM-dd 23:59:59
     */
    public static String getTodayLastSecond() {
        return getDayLastSecond(now());
    }

    /**
     * 获取一天的结束时间
     *
     * @param date 指定date
     * @return yyyy-MM-dd 23:59:59
     */
    public static String getDayLastSecond(Date date) {
        return FORMAT_YYYY_MM_DD.format(date) + " 23:59:59";
    }

    /**
     * 获取当前时刻在n天前的Date对象
     *
     * @return Date
     */
    public static Date getNDaysAgo(int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, -n);
        return cal.getTime();
    }

    /**
     * 获取当前时刻在n天前的时间字符串
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNDaysAgoTimeStr(int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, -n);
        Date date = cal.getTime();
        return FORMAT_YYYY_MM_DD_HH_MM_SS.format(date);
    }

    /**
     * 获取当前时刻在n天前的日期字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getNDaysAgoDateStr(int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, -n);
        Date date = cal.getTime();
        return FORMAT_YYYY_MM_DD.format(date);
    }

    /**
     * 获取当前时刻在n天后的Date对象
     *
     * @return Date
     */
    public static Date getNDaysLater(int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, n);
        return cal.getTime();
    }

    /**
     * 获取当前时刻在n天后的时间字符串
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNDaysLaterTimeStr(int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, n);
        Date date = cal.getTime();
        return FORMAT_YYYY_MM_DD_HH_MM_SS.format(date);
    }

    /**
     * 获取当前时刻在n天后的日期字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getNDaysLaterDateStr(int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, n);
        Date date = cal.getTime();
        return FORMAT_YYYY_MM_DD.format(date);
    }

    /**
     * 获取昨天的当前时刻日期
     *
     * @return Date
     */
    public static Date getYesterday() {
        return getNDaysAgo(1);
    }

    /**
     * 获取昨天的当前时刻字符串
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getYesterdayTimeStr() {
        return getNDaysAgoTimeStr(1);
    }

    /**
     * 获取昨天的日期字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getYesterdayDateStr() {
        return getNDaysAgoDateStr(1);
    }

    /**
     * 获取明天的当前时刻日期
     *
     * @return Date
     */
    public static Date getTomorrow() {
        return getNDaysLater(1);
    }

    /**
     * 获取明天的当前时刻字符串
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTomorrowTimeStr() {
        return getNDaysLaterTimeStr(1);
    }

    /**
     * 获取明天的日期字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getTomorrowDateStr() {
        return getNDaysLaterDateStr(1);
    }

    /**
     * 获取指定日期当月的第一天
     * （时分秒为date时分秒）
     */
    public static Date getStartDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, 1);
        return cDay.getTime();
    }

    /**
     * 获取这个月的第一天
     * （时分秒为date时分秒）
     */
    public static Date getStartDayOfMonth() {
        return getStartDayOfMonth(now());
    }

    /**
     * 获取指定日期当月的最后一天
     * （时分秒为date时分秒）
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        return ca.getTime();
    }

    /**
     * 获取这个月的最后一天
     * （时分秒为date时分秒）
     */
    public static Date getLastDayOfMonth() {
        return getLastDayOfMonth(now());
    }

}
