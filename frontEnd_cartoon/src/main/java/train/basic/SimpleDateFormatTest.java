package train.basic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author chengqj
 * @Date 2020/9/3 14:35
 * @Desc
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) {
        Date now = Calendar.getInstance().getTime();
        // 替换pattern
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.toPattern() + " --> " + sdf.format(now));
        sdf.applyPattern("yyyy-MM");
        System.out.println(sdf.toPattern() + " --> " + sdf.format(now));
    }
}
