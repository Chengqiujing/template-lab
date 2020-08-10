package com.chengqj.springbootsenior.tcp.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;

/**
 * @Author chengqiujing
 * @Date 2020/7/10 1:54
 * @Desc
 */
public class Test {
    public static void main(String[] args) {

//        String a = "123asdf123asdf";
//        String replace = a.replaceAll("[0-9]+", "*");
//        System.out.println(replace);
//
//        Expression e = new Expression("true");
//        System.out.println(e.eval().intValue());
//
//        Expression e1 = new Expression("IF(80.0>50,false,true)");
//        System.out.println(e1.eval().intValue());
//
//
//        String  aa = "123123$continue('P09\\R\\SB\\DB1_DDKWH.PV',2)>50";
//        aa = aa.replaceAll("\\$continue\\('P09\\\\R\\\\SB\\\\DB1_DDKWH.PV',2\\)>50","false");
//        System.out.println(aa);


//        Set<String> set = new TreeSet<>();
//        set.add("2020-02-02");
//        set.add("2020-01-05");
//        set.add("2020-01-03");
//        set.add("2020-01-09");
//        set.add("2020-03-04");
//        set.add("2020-07-05");
//        set.add("2020-07-02");
//        set.add("2020-06-09");
//        set.add("2020-10-09");
//        set.add("2020-11-09");
//        set.add("2020-12-09");
//        System.out.println(set);


//        Calendar instance = Calendar.getInstance();
//        instance.setFirstDayOfWeek(Calendar.MONDAY);
//        instance.set(2019,11,30); // 周日
//        System.out.println(instance.getWeeksInWeekYear());
//        System.out.println(instance.get(Calendar.WEEK_OF_YEAR));
//        System.out.println(instance.getWeekYear());
//
//        // 会不会出现多一周少一周的问题,不会
//
//
//        Calendar instance1 = Calendar.getInstance();
//        instance1.setFirstDayOfWeek(Calendar.MONDAY);
//        instance1.set(2020,0,1); // 周三
//        System.out.println(instance1.getWeeksInWeekYear());
//        System.out.println(instance1.get(Calendar.WEEK_OF_YEAR));
//        System.out.println(instance1.getWeekYear());


        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR,2019);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.getWeeksInWeekYear()); // ?


        calendar.set(Calendar.YEAR,2018);
        System.out.println(calendar.getWeeksInWeekYear());
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR)); // ?

        calendar.set(Calendar.YEAR,2017);
        System.out.println(calendar.getWeeksInWeekYear());
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR)); // ?

        calendar.set(Calendar.YEAR,2016);
        System.out.println(calendar.getWeeksInWeekYear());
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR)); // ?

        //System.out.println(calendar.getWeeksInWeekYear());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        now = now.with(ChronoField.DAY_OF_WEEK, 1); // 设置周一
        System.out.println(now.format(dateTimeFormatter));


        LocalDate now1 = LocalDate.now();
        System.out.println(now1.getDayOfMonth()); // 获取本天是多少号
        System.out.println(now1.get(ChronoField.DAY_OF_MONTH)); // 等同于上边
        System.out.println(now1.lengthOfMonth()); // 获取本月长度


    }
}
