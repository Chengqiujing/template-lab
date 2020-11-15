package train.interview;

/**
 * @Author chengqj
 * @Date 2020/11/15 10:55
 * @Desc
 */
public class StringEquals {
    /**
     * String - .intern() 表示从字符常量池中提取字符串
     * new String("aabbcc") 的行为创建了2个对象,一个在堆中,一个在常量池中,元空间(以前的永久代)
     */
    public static void main(String[] args) throws InterruptedException {
        String m = "A";
        change(m);
        System.out.println(m);
        String a = "abc";
        String b = new String("abc");
        System.out.println(a == b.intern());
    }

    static void change(String m) {
        m = m.toLowerCase();

    }


}
