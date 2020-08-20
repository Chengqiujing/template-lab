package train.basic;

/**
 * @Author chengqj
 * @Date 2020/8/20 17:36
 * @Desc
 */
public class InnerClass {
    /**
     * blockedInnerCalss(局部内部类) 因为外部变量为什么一定要将外部变量final化
     */

    private int b  = 10;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void getInnerCalss(){
        int a = 10;
        class Inner {
            void deal(){
                b = 9;
                //a = 11;

            }
        }
        Inner inner = new Inner();
        inner.deal();
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        innerClass.getInnerCalss();;
        System.out.println(innerClass.getB());
    }
}
