package train.basic;

public class StringTest {
    public static void main(String[] args) {
        //1.证明常量池：java会确保每一个字符串常量都只有一个副本
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he" + "llo";
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//true

        //当涉及到引用而非字面量的时候，编译器则无法在编译期间确定字面量，则会在程序执行时，将“+”拼接，转换成new StringBuilder的形式进行拼接。
        //这样就只会产生新对象
        String s4 = "he";
        String s5 = "llo";
        String s6 = s4 + s5;//新对象
        String s7 = "he" + s5;//新对象
        System.out.println(s4 + s5 == s6);
        System.out.println(s1 == s6);
        System.out.println(s7 == s6);
        System.out.println(s1 == s7);
    }
}
