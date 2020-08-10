package train.question;

import java.util.concurrent.TimeUnit;

/**
 * @Author chengqiujing
 * @Date 2020/5/5 16:55
 * @Desc
 */
public class VolatileTest {

    private /*volatile*/ boolean flag = true;

    void print(){
        while(flag) { // 此处注意 方法中不能有sleep代码或者输出代码，这些会影响可见性测试。
//            try {
//                //TimeUnit.SECONDS.sleep(1);
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //System.out.println("running");
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();

        Thread t = new Thread(volatileTest::print,"test1");
        t.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("change flag");
        //volatileTest.flag = false; // 为什么main里可以点出私有变量
        volatileTest.setFlag(false);

        t.join();
    }

}
