package train.question;

/**
 * 中断会打断sleep状态，并抛出被打断异常
 */
public class ThreadSleep {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("run 进入 100s 睡眠");
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("开始死循环");
//                while(true){
//                    if(Thread.currentThread().isInterrupted()){
//                        return;
//                    }
//                }
            }
        });
        t.start();
        System.out.println("main 进入 10s 睡眠");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 开始中断线程");
        t.interrupt();

    }


}
