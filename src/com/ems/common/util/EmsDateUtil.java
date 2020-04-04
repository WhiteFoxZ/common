// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.util.Locale;
import java.util.Date;
import java.util.TimeZone;
import java.util.SimpleTimeZone;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class EmsDateUtil
{
    private static Calendar date;
    
    static {
        EmsDateUtil.date = Calendar.getInstance();
    }
    
    public static String getYear() {
        final int goc = new GregorianCalendar().get(1);
        return Integer.toString(goc);
    }
    
    public static String getMonth() {
        String m_Month = Integer.toString(new GregorianCalendar().get(2) + 1);
        if (m_Month.length() == 1) {
            m_Month = "0" + m_Month;
        }
        return m_Month;
    }
    
    public static String getDay() {
        String m_Day = Integer.toString(new GregorianCalendar().get(5));
        if (m_Day.length() == 1) {
            m_Day = "0" + m_Day;
        }
        return m_Day;
    }
    
    public static String addDay(final int nowDate, final int gap, String frm) {
        if (frm != null && frm.equals("")) {
            frm = "yyyyMMdd";
        }
        final String rtnDay = null;
        final int yearx = nowDate / 10000;
        final int monthx = (nowDate - yearx * 10000) / 100;
        final int dayx = nowDate - yearx * 10000 - monthx * 100;
        final int day = 0;
        final Calendar date = Calendar.getInstance();
        date.set(yearx, monthx - 1, dayx);
        date.set(5, date.get(5) + gap);
        final int millisPerHour = 3600000;
        final SimpleDateFormat format = new SimpleDateFormat(frm);
        final SimpleTimeZone timeZone = new SimpleTimeZone(9 * millisPerHour, "Asia/Seoul");
        format.setTimeZone(timeZone);
        return format.format(date.getTime());
    }
    
    public static String addDay(final String nowDate, final int gap, final String frm) {
        return addDay(Integer.parseInt(nowDate.replaceAll("-", "")), gap, frm);
    }
    
    public static String getLastDateAtMonth(final int yyyy, final int mm) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(yyyy, mm - 1, 1);
        calendar.add(2, 1);
        calendar.add(5, -1);
        final int date = calendar.get(5);
        return EmsNumberUtil.format(date, "00");
    }
    
    public static String getLastDateAtMonth(final String yyyy, final String mm) {
        final String ss = getLastDateAtMonth(Integer.parseInt(yyyy), Integer.parseInt(mm));
        return ss;
    }
    
    public static String getTimeStampString(final String format) {
        final int millisPerHour = 3600000;
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        final SimpleTimeZone timeZone = new SimpleTimeZone(9 * millisPerHour, "Asia/Seoul");
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date(System.currentTimeMillis()));
    }
    
    public static String getCurrentDate(final String fmt) {
        return getTimeStampString(fmt);
    }
    
    public static String getCurrentDate(final String fmt, final int day) {
        final String today = getCurrentDate("yyyyMMdd");
        final String addDay = addDay(today, day, fmt);
        return addDay;
    }
    
    public static String getCurrentDate(final String fmt, final Date date) {
        String stringDate = "";
        try {
            final int millisPerHour = 3600000;
            final SimpleDateFormat format = new SimpleDateFormat(fmt);
            final SimpleTimeZone timeZone = new SimpleTimeZone(9 * millisPerHour, "Asia/Seoul");
            format.setTimeZone(timeZone);
            stringDate = format.format(date);
        }
        catch (Exception ex) {}
        return stringDate;
    }
    
    public static String getCurrentDate(final String fmt, final String date) {
        return getCurrentDate(fmt, date, Locale.KOREA);
    }
    
    public static String getCurrentDate(final String fmt, final String date, final Locale lo) {
        String stringDate = "";
        if (date.length() == 8) {
            final int year = Integer.parseInt(date.substring(0, 4));
            final int month = Integer.parseInt(date.substring(4, 6)) - 1;
            final int day = Integer.parseInt(date.substring(6, 8));
            final Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            try {
                final int millisPerHour = 3600000;
                final SimpleDateFormat format = new SimpleDateFormat(fmt);
                final SimpleTimeZone timeZone = new SimpleTimeZone(9 * millisPerHour, "Asia/Seoul");
                format.setTimeZone(timeZone);
                stringDate = format.format(date);
            }
            catch (Exception ex) {}
        }
        else {
            stringDate = date;
        }
        return stringDate;
    }
    
    public static String addMonths(final String day, final int monthGap, final String frm) {
        final Calendar utilDate = Calendar.getInstance();
        final SimpleDateFormat sdf_out = new SimpleDateFormat(frm);
        final int setYear = Integer.parseInt(day.substring(0, 4));
        final int setMonth = Integer.parseInt(day.substring(4, 6)) + monthGap - 1;
        final int setDay = Integer.parseInt(day.substring(6, 8));
        utilDate.set(setYear, setMonth, setDay);
        final String preFix = sdf_out.format(utilDate.getTime());
        return preFix;
    }
    
    public static String getDate(final int year, final int month, final int day) {
        final Calendar utilDate = Calendar.getInstance();
        utilDate.set(year, month - 1, day);
        return getCurrentDate("E", utilDate.getTime());
    }
    
    public static int getDiffDay(String startDay, String endDay) {
        startDay = startDay.replace("-", "");
        endDay = endDay.replace("-", "");
        final int f_year = Integer.parseInt(startDay.substring(0, 4));
        final int f_month = Integer.parseInt(startDay.substring(4, 6));
        final int f_date = Integer.parseInt(startDay.substring(6, 8));
        final int e_year = Integer.parseInt(endDay.substring(0, 4));
        final int e_month = Integer.parseInt(endDay.substring(4, 6));
        final int e_date = Integer.parseInt(endDay.substring(6, 8));
        final Calendar st = Calendar.getInstance();
        final Calendar ed = Calendar.getInstance();
        st.set(f_year, f_month - 1, f_date);
        ed.set(e_year, e_month - 1, e_date);
        final long diff = ed.getTimeInMillis() - st.getTimeInMillis();
        final long diffday = diff / 86400000L;
        return (int)diffday;
    }
    
    public static int getDiffDay(final int startDay, final int endDay) {
        return getDiffDay(new StringBuilder().append(startDay).toString(), new StringBuilder().append(endDay).toString());
    }
    
    public static String getmmm(final String mmm) {
        final EmsHashtable map = new EmsHashtable();
        map.put("JAN", "01");
        map.put("FEB", "02");
        map.put("MAR", "03");
        map.put("APR", "04");
        map.put("MAY", "05");
        map.put("JUN", "06");
        map.put("JUL", "07");
        map.put("AUG", "08");
        map.put("SEP", "09");
        map.put("OCT", "10");
        map.put("NOV", "11");
        map.put("DEC", "12");
        return map.getString(mmm);
    }
    
    public static String getMMM(final String mmm) {
        final EmsHashtable map = new EmsHashtable();
        map.put("01", "JAN");
        map.put("02", "FEB");
        map.put("03", "MAR");
        map.put("04", "APR");
        map.put("05", "MAY");
        map.put("06", "JUN");
        map.put("07", "JUL");
        map.put("08", "AUG");
        map.put("09", "SEP");
        map.put("10", "OCT");
        map.put("11", "NOV");
        map.put("12", "DEC");
        return map.getString(mmm);
    }
    
    public static void main(final String[] args) {
        System.out.println(getDiffDay("2016-11-01", "2016-10-01"));
    }
}
