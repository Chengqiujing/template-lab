package train.basic;

public class BreakTest {
    /**
     * break测试的结论
     * break只跳出一层循环，跳出多层循环时，需要添加标记，如果标记添加在了本层，则如同不加。
     * @param args
     */
    public static void main(String[] args) {
        a:
        for (int i = 0; i < 10; i++) {
            System.out.println(1);

            for (int i1 = 0; i1 < 10; i1++) {
                System.out.println(2);
                break a;
            }
        }
    }
}
