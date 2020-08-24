package train.question;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author chengqj
 * @Date 2020/8/17 15:02
 * @Desc
 */
public class CalendarQuestion {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

        // 同一个calendar实例,设置两个时间,会不会互相影响
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 1); // 1月
        Date time = calendar.getTime();
        System.out.println(sdf.format(time));

//        Calendar calendar1 = Calendar.getInstance();
        calendar.set(2022, 1, 2); // 2月
        Date time1 = calendar.getTime();
        System.out.println(sdf.format(time1)); // 结论: 不会

        // 测试对同一个calendar实例加入两个date,会不会互相影响
        calendar.setTime(time1);
        System.out.println(calendar.get(Calendar.YEAR)); // 2022
        calendar.setTime(time);
        System.out.println(calendar.get(Calendar.YEAR)); // 2020
        // 结论: 不会
    }
}
