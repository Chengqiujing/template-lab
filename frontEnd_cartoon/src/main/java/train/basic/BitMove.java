package train.basic;

public class BitMove {
    public static void main(String[] args) {
        //左移 右移 考虑符号
        System.out.println(-1 << 2);
        System.out.println(-1 >> 2);
        System.out.println(1 << 2);
        System.out.println(1 >> 2);

        //只有 >>> 无符号右移  不考虑符号位以0填充
        //这样整数不受影响，负数受影响，因为负数符号位是1
        System.out.println(-1 >>> 2);

    }
}
