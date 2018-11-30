/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DateTest
 * Author:   wh
 * Date:     2018/9/20 0:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.test;
import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.Set;


public class DateTest {
    private static DateFormat datafFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

        Date first = firstMonthDate(new Date());
        Map<Integer,WeekRange> maps = new HashMap<Integer, WeekRange>();
        getWeekBeginAndEnd(1,first,maps);

        Set<Integer> set = maps.keySet();
        for(Integer key : set){
            WeekRange range = maps.get(key);
            System.out.println(String.format("第%d周,开始日期：%s,结束日期：%s", key,format(range.getBegin()),format(range.getEnd())));
        }
    }

    // 月初
    public static Date firstMonthDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    // 月末
    public static Date lastMonthDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    // 星期几
    public static int week(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    // 下一天
    public static Date nextDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    // 每周开始结束时间
    public static void getWeekBeginAndEnd(int index,Date currentDate,Map<Integer,WeekRange> maps){
        //月末
        Date lastMonthDate = lastMonthDate(currentDate);
        int week = week(currentDate);
        if(null == maps){
            WeekRange range = new WeekRange(currentDate, currentDate);
            maps = new HashMap<Integer, WeekRange>();
            maps.put(index,range);
        }else{
            WeekRange range = maps.get(index);
            if(null == range){
                range = new WeekRange(currentDate);
            }
            range.setEnd(currentDate);
            maps.put(index,range);
        }

        if(currentDate.equals(lastMonthDate)){
            return;
        }

        if(week == 0){
            index++;
        }

        getWeekBeginAndEnd(index,nextDate(currentDate),maps);
    }

    public static String format(Date date){
        return datafFormat.format(date);
    }
}

class WeekRange{
    Date begin;//周开始日
    Date end;//周结束日
    public WeekRange(Date begin) {
        super();
        this.begin = begin;
    }
    public WeekRange(Date begin, Date end) {
        super();
        this.begin = begin;
        this.end = end;
    }
    public Date getBegin() {
        return begin;
    }
    public void setBegin(Date begin) {
        this.begin = begin;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
}

