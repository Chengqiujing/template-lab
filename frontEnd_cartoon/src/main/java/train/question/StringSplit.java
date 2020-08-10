package train.question;

/**
 * @Author chengqiujing
 * @Date 2020/5/5 11:05
 * @Desc
 */
public class StringSplit {

    public static void main(String[] args) {
        String str = "a.b.c..";

        String[] split = str.split("\\.");
        // 预期大于3 实际等于3 （来自阿里巴巴开发手册泰山版）
        System.out.println(split.length);
    }
}
