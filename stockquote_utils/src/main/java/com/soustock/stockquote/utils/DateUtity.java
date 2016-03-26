package com.soustock.stockquote.utils;

import com.soustock.stockquote.exception.BusinessException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xuyufei on 2016/03/06.
 * 时间工具类
 */
public class DateUtity {

    private final static DateFormat xueqiuFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US );

    public static Date parseXueqiuFormatToDate(String xueqiuDtStr) throws ParseException {
        return xueqiuFormat.parse(xueqiuDtStr);
    }

//    private final static DateFormat stdFormat = new SimpleDateFormat("yyyyMMdd");
    public static String dateToDateStr(Date dt){
        DateFormat stdFormat = new SimpleDateFormat("yyyyMMdd");
        return stdFormat.format(dt);
    }

    public static Date parseDateStrToDate(String dtStr) throws BusinessException {
        try {
            DateFormat stdFormat = new SimpleDateFormat("yyyyMMdd");
            return stdFormat.parse(dtStr);
        }
        catch (ParseException ex){
            throw new BusinessException("解析失败，当前字符串为:"+dtStr, ex);
        }
    }

    public static int getYear(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        return calendar.get(Calendar.YEAR);
    }

    public static int getJidu(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        return calendar.get(Calendar.MONTH) /3 + 1;
    }

    public static Date StrToDateTime(String dtStr){
        if (NullCheckUtity.stringIsNull(dtStr)) return null ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dtStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date StrToDate(String dtStr){
        if (NullCheckUtity.stringIsNull(dtStr)) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dtStr);
        } catch (ParseException e) {
            return null;
        }
    }

}
