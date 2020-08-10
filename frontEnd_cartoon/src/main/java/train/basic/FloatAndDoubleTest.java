package train.basic;

public class FloatAndDoubleTest {
    public static void main(String[] args) {
        double d = 0.0/0;
        System.out.println(d);//NaN
        System.out.println(5/0.0);//正无穷大
        System.out.println(-5/0.0);//负无穷大

        System.out.println(3.1%3);
    }
}
