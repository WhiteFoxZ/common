// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.io.PrintStream;
import java.util.Calendar;

public class YunLunCalendar
{
    public final int SOLAR_YEAR = 1;
    public final int SOLAR_MONTH = 2;
    public final int SOLAR_DATE = 3;
    public final int LUNAR_YEAR = 4;
    public final int LUNAR_MONTH = 5;
    public final int LUNAR_DATE = 6;
    public final int IS_YUNDAL = 7;
    private final int[][] matchTable;
    private int year;
    private int month;
    private int date;
    private int lunarYear;
    private int lunarMonth;
    private int lunarDate;
    private boolean leap;
    private final String[] lunarYuk;
    private final String[] lunarGap;
    private final String[] lunarDdi;
    private int[] lunarMonthDay;
    private final String[] lunarWeekDay;
    
    public YunLunCalendar(final String year, final String month, final String date) {
        this.matchTable = new int[][] { { 1, 2, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 3, 1, 2, 1, 2, 1, 2, 1 }, { 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 2, 1 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 3, 1, 2, 2, 1, 2, 2 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 2, 3, 1, 2, 1, 2, 1, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 0 }, { 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 1, 2, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 1 }, { 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 4, 1, 2, 1, 2, 1, 1, 2 }, { 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 1, 2, 1, 1, 4, 1, 2, 2, 1, 2 }, { 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 4, 1, 1, 2, 1, 2, 1 }, { 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0 }, { 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2 }, { 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0 }, { 2, 2, 2, 3, 2, 1, 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 2, 3, 2, 1, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 4, 1, 2, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2, 2 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 1, 2, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 1, 2, 2, 3, 2, 1, 2, 1, 1, 2, 1, 2, 1 }, { 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2 }, { 1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 3, 2, 2, 1, 2, 2, 2, 1 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0 }, { 2, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 0 }, { 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 1, 4, 1, 1, 2, 1, 2, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 1 }, { 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 } };
        this.lunarYuk = new String[] { "\uac11", "\uc744", "\ubcd1", "\uc815", "\ubb34", "\uae30", "\uacbd", "\uc2e0", "\uc784", "\uacc4" };
        this.lunarGap = new String[] { "\uc790", "\ucd95", "\uc778", "\ubb18", "\uc9c4", "\uc0ac", "\uc624", "\ubbf8", "\uc2e0", "\uc720", "\uc220", "\ud574" };
        this.lunarDdi = new String[] { "\uc950\ub760", "\uc18c\ub760", "\ubc94\ub760", "\ud1a0\ub07c\ub760", "\uc6a9\ub760", "\ubc40\ub760", "\ub9d0\ub760", "\uc591\ub760", "\uc794\ub098\ube44\ub760", "\ub2ed\ub760", "\uac1c\ub760", "\ub3fc\uc9c0\ub760" };
        this.lunarMonthDay = new int[] { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.lunarWeekDay = new String[] { "\uc77c\uc694\uc77c", "\uc6d4\uc694\uc77c", "\ud654\uc694\uc77c", "\uc218\uc694\uc77c", "\ubaa9\uc694\uc77c", "\uae08\uc694\uc77c", "\ud1a0\uc694\uc77c" };
        this.year = Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.date = Integer.parseInt(date);
        this.convert();
    }
    
    public YunLunCalendar(final int year, final int month, final int date) {
        this.matchTable = new int[][] { { 1, 2, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 3, 1, 2, 1, 2, 1, 2, 1 }, { 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 2, 1 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 3, 1, 2, 2, 1, 2, 2 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 2, 3, 1, 2, 1, 2, 1, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 0 }, { 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 1, 2, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 1 }, { 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 4, 1, 2, 1, 2, 1, 1, 2 }, { 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 1, 2, 1, 1, 4, 1, 2, 2, 1, 2 }, { 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 4, 1, 1, 2, 1, 2, 1 }, { 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0 }, { 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2 }, { 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0 }, { 2, 2, 2, 3, 2, 1, 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 2, 3, 2, 1, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 4, 1, 2, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2, 2 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 1, 2, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 1, 2, 2, 3, 2, 1, 2, 1, 1, 2, 1, 2, 1 }, { 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2 }, { 1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 3, 2, 2, 1, 2, 2, 2, 1 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0 }, { 2, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 0 }, { 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 1, 4, 1, 1, 2, 1, 2, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 1 }, { 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 } };
        this.lunarYuk = new String[] { "\uac11", "\uc744", "\ubcd1", "\uc815", "\ubb34", "\uae30", "\uacbd", "\uc2e0", "\uc784", "\uacc4" };
        this.lunarGap = new String[] { "\uc790", "\ucd95", "\uc778", "\ubb18", "\uc9c4", "\uc0ac", "\uc624", "\ubbf8", "\uc2e0", "\uc720", "\uc220", "\ud574" };
        this.lunarDdi = new String[] { "\uc950\ub760", "\uc18c\ub760", "\ubc94\ub760", "\ud1a0\ub07c\ub760", "\uc6a9\ub760", "\ubc40\ub760", "\ub9d0\ub760", "\uc591\ub760", "\uc794\ub098\ube44\ub760", "\ub2ed\ub760", "\uac1c\ub760", "\ub3fc\uc9c0\ub760" };
        this.lunarMonthDay = new int[] { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.lunarWeekDay = new String[] { "\uc77c\uc694\uc77c", "\uc6d4\uc694\uc77c", "\ud654\uc694\uc77c", "\uc218\uc694\uc77c", "\ubaa9\uc694\uc77c", "\uae08\uc694\uc77c", "\ud1a0\uc694\uc77c" };
        this.year = year;
        this.month = month;
        this.date = date;
        this.convert();
    }
    
    public YunLunCalendar() {
        this.matchTable = new int[][] { { 1, 2, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 3, 1, 2, 1, 2, 1, 2, 1 }, { 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 2, 1 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 3, 1, 2, 2, 1, 2, 2 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 2, 3, 1, 2, 1, 2, 1, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 0 }, { 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 1, 2, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 1 }, { 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 4, 1, 2, 1, 2, 1, 1, 2 }, { 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 1, 2, 1, 1, 4, 1, 2, 2, 1, 2 }, { 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 4, 1, 1, 2, 1, 2, 1 }, { 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0 }, { 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 1, 2 }, { 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2 }, { 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0 }, { 2, 2, 2, 3, 2, 1, 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 2, 3, 2, 1, 1, 2 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 4, 1, 2, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2, 2 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0 }, { 2, 1, 2, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 1, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 2, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0 }, { 1, 2, 2, 3, 2, 1, 2, 1, 1, 2, 1, 2, 1 }, { 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2 }, { 1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 1, 2, 1, 3, 2, 2, 1, 2, 2, 2, 1 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0 }, { 2, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2 }, { 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 1, 2, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0 }, { 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1 }, { 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 1, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 0 }, { 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0 }, { 2, 1, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2 }, { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0 }, { 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0 }, { 2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2 }, { 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 0 }, { 1, 2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0 }, { 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0 }, { 1, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 0 }, { 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1 }, { 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0 }, { 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 1, 4, 1, 1, 2, 1, 2, 2 }, { 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0 }, { 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0 }, { 2, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 1 }, { 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0 }, { 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0 }, { 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2 }, { 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0 } };
        this.lunarYuk = new String[] { "\uac11", "\uc744", "\ubcd1", "\uc815", "\ubb34", "\uae30", "\uacbd", "\uc2e0", "\uc784", "\uacc4" };
        this.lunarGap = new String[] { "\uc790", "\ucd95", "\uc778", "\ubb18", "\uc9c4", "\uc0ac", "\uc624", "\ubbf8", "\uc2e0", "\uc720", "\uc220", "\ud574" };
        this.lunarDdi = new String[] { "\uc950\ub760", "\uc18c\ub760", "\ubc94\ub760", "\ud1a0\ub07c\ub760", "\uc6a9\ub760", "\ubc40\ub760", "\ub9d0\ub760", "\uc591\ub760", "\uc794\ub098\ube44\ub760", "\ub2ed\ub760", "\uac1c\ub760", "\ub3fc\uc9c0\ub760" };
        this.lunarMonthDay = new int[] { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.lunarWeekDay = new String[] { "\uc77c\uc694\uc77c", "\uc6d4\uc694\uc77c", "\ud654\uc694\uc77c", "\uc218\uc694\uc77c", "\ubaa9\uc694\uc77c", "\uae08\uc694\uc77c", "\ud1a0\uc694\uc77c" };
        final Calendar cal = Calendar.getInstance();
        this.year = cal.get(1);
        this.month = cal.get(2) - 1;
        this.date = cal.get(5);
        this.convert();
    }
    
    private void convert() {
        final int[] dt = new int[163];
        for (int i = 0; i < this.matchTable.length; ++i) {
            dt[i] = 0;
            for (int j = 0; j < 12; ++j) {
                if (this.matchTable[i][j] % 2 == 1) {
                    final int[] array = dt;
                    final int n = i;
                    array[n] += 29;
                }
                else {
                    final int[] array2 = dt;
                    final int n2 = i;
                    array2[n2] += 30;
                }
            }
            if (this.matchTable[i][12] == 0) {
                final int[] array3 = dt;
                final int n3 = i;
                array3[n3] += 0;
            }
            else if (this.matchTable[i][12] % 2 == 1) {
                final int[] array4 = dt;
                final int n4 = i;
                array4[n4] += 29;
            }
            else {
                final int[] array5 = dt;
                final int n5 = i;
                array5[n5] += 30;
            }
        }
        final int td1 = 686686;
        final int k11 = this.year - 1;
        int td2 = k11 * 365 + (int)(k11 / 4.0) - (int)(k11 / 100.0) + (int)(k11 / 400.0);
        if (this.year % 400 == 0 || (this.year % 100 != 0 & this.year % 4 == 0)) {
            this.lunarMonthDay[1] = 29;
        }
        else {
            this.lunarMonthDay[1] = 28;
        }
        for (int i = 0; i < this.month - 1; ++i) {
            td2 += this.lunarMonthDay[i];
        }
        td2 += this.date;
        int td3;
        int td4;
        int t1;
        for (td3 = td2 - td1 + 1, td4 = dt[0], t1 = 0, t1 = 0; t1 < 163 && td3 > td4; td4 += dt[t1 + 1], ++t1) {}
        this.lunarYear = t1 + 1881;
        td4 -= dt[t1];
        td3 -= td4;
        int jcount = 12;
        if (this.matchTable[t1][12] != 0) {
            jcount = 13;
        }
        int m2 = 0;
        int t2;
        int m3;
        for (t2 = 0, t2 = 0; t2 < jcount; ++t2) {
            if (this.matchTable[t1][t2] <= 2) {
                ++m2;
                m3 = this.matchTable[t1][t2] + 28;
            }
            else {
                m3 = this.matchTable[t1][t2] + 26;
            }
            if (td3 <= m3) {
                break;
            }
            td3 -= m3;
        }
        final int m4 = t2;
        this.lunarMonth = m2;
        this.lunarDate = td3;
        final int w = td2 % 7;
        t1 = (td2 + 4) % 10;
        t2 = (td2 + 2) % 12;
        final int ti1 = (this.lunarYear + 6) % 10;
        final int tj1 = (this.lunarYear + 8) % 12;
        if (this.matchTable[this.lunarYear - 1881][12] > 2 & this.matchTable[this.lunarYear - 1881][m4] > 2) {
            this.leap = true;
        }
    }
    
    public void set(final int field, final int value) {
        switch (field) {
            case 1: {
                this.year = value;
                this.convert();
                break;
            }
            case 2: {
                this.month = value;
                this.convert();
                break;
            }
            case 3: {
                this.date = value;
                this.convert();
                break;
            }
            case 4: {
                this.lunarYear = value;
                break;
            }
            case 5: {
                this.lunarMonth = value;
                break;
            }
            case 6: {
                this.lunarDate = value;
                break;
            }
        }
    }
    
    public int get(final int field) {
        switch (field) {
            case 1: {
                return this.year;
            }
            case 2: {
                return this.month;
            }
            case 3: {
                return this.date;
            }
            case 4: {
                return this.lunarYear;
            }
            case 5: {
                return this.lunarMonth;
            }
            case 6: {
                return this.lunarDate;
            }
            case 7: {
                return this.leap ? 1 : 0;
            }
            default: {
                return -1;
            }
        }
    }
    
    public String getYukGap() {
        String gap = this.lunarYuk[(this.lunarYear - 1881 + 7) % 10];
        gap = String.valueOf(gap) + this.lunarGap[(this.lunarYear - 1881 + 5) % 12];
        return gap;
    }
    
    public String getDdi() {
        return this.lunarDdi[(this.lunarYear - 1881 + 5) % 12];
    }
    
    public boolean isLeapYear(final String ymd) throws NumberFormatException {
        int year = 0;
        boolean isLeap = false;
        try {
            year = Integer.parseInt(ymd.substring(0, 4));
            isLeap = ((year & 0x3) == 0x0 && (year % 100 != 0 || (year + 300) % 400 == 0));
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            isLeap = false;
        }
        return isLeap;
    }
    
    public static void main(final String[] args) {
        final YunLunCalendar s = new YunLunCalendar(2008, 2, 7);
        s.convert();
        final PrintStream out = System.out;
        final YunLunCalendar yunLunCalendar = s;
        s.getClass();
        out.println(yunLunCalendar.get(4));
        final PrintStream out2 = System.out;
        final YunLunCalendar yunLunCalendar2 = s;
        s.getClass();
        out2.println(yunLunCalendar2.get(5));
        final PrintStream out3 = System.out;
        final YunLunCalendar yunLunCalendar3 = s;
        s.getClass();
        out3.println(yunLunCalendar3.get(6));
    }
}
