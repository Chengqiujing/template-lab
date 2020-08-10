package train.basic;

import java.time.*;
import java.util.Calendar;

public class TimeTest {
    public static void main(String[] args) {
        //Clock 时间类
        Clock clock = Clock.systemUTC();
        //当前时刻
        System.out.println(clock.instant());
        //毫秒数(跟System的相同)
        System.out.println(clock.millis());


        //Duration 时间段
        Duration duration = Duration.ofSeconds(6000);
        //这6000秒与其他时间段的换算
        //相当于多少分
        System.out.println(duration.toMinutes());
        //相当于多少小时
        System.out.println(duration.toHours());//结果：1 这个有问题 不精确
        //相当于多少天
        System.out.println(duration.toDays());//结果：0 这个有问题 不精确

        //在Clock的基础上增加上述时间段
        Clock clock1 = Clock.offset(clock,duration);



        //Instant 当前时间
        Instant instant = Instant.now();
        System.out.println(instant);
        //instant 增加6000秒 即100分钟
        Instant instant1 = instant.plusSeconds(6000);
        System.out.println(instant1);

        //根据字符串解析对象
        Instant instant2 = Instant.parse("2019-09-09T10:12:12.123Z");
        System.out.println(instant2);

        //增加
        Instant instant3 = instant.plus(Duration.ofHours(5).plusMinutes(3));
        System.out.println(instant3);
        //减少
        Instant instant4 = instant.minus(Duration.ofDays(1).minusHours(4));

        //关于LocalDate的用法
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        //huode 2014的第146天
        localDate = LocalDate.ofYearDay(2014,146);
        System.out.println(localDate);
        //设置为2014年5月21日
        localDate = LocalDate.of(2014, Month.MAY,21);
        System.out.println(localDate);

        //LocalTime
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        //设置为22：33
        localTime = LocalTime.of(22,33);
        System.out.println(localTime);

        //指定为一天的第5503秒
        localTime = LocalTime.ofSecondOfDay(5503);
        System.out.println(localTime);

        //...还有很多方法
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        localDateTime = localDateTime.plusDays(1).plusHours(4).plusSeconds(12);
        System.out.println(localDateTime);
        System.out.println(localDateTime.getMonth());

        //Year YearMonth MonthDay
        Year year = Year.now();
        System.out.println(year);
        year.plusYears(5);

        YearMonth yearMonth = year.atMonth(5);
        yearMonth = yearMonth.plusMonths(3);
        System.out.println(yearMonth.getMonth());
        MonthDay monthDay = MonthDay.now();
        System.out.println(monthDay);

        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.MONTH));
    }
}
