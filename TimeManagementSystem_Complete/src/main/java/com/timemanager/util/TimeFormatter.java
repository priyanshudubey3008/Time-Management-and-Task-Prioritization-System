package com.timemanager.util;

public final class TimeFormatter {
    private TimeFormatter(){}
    public static String format(long seconds){
        long h=seconds/3600, m=(seconds%3600)/60, s=seconds%60;
        return String.format("%02dh %02dm %02ds",h,m,s);
    }
}
