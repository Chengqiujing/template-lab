package train.question;

public class StepQuestion {
    /**
     * 上20阶楼梯 每次只能上1阶或者2阶
     * @param args
     */
    public static void main(String[] args) {
        int a =1,b=2,temp=3;
        for (int i = 3; i <= 20; i++) {
            temp = a +b;
            a = b;
            b = temp;
        }
        System.out.println(temp);
    }
}
