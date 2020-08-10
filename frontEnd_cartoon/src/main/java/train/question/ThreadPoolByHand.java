package train.question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPoolByHand {
    //典型的生产者-消费者模式

    //1.任务阻塞队列
    BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque();

    //2.线程队列
    List<WorkThread> threadList = new ArrayList<>();


    //3.初始化函数（单例）


    //4.内部执行


    //5.工厂化获取线程池


    //6.线程类
    class WorkThread extends Thread{


        @Override
        public void run() {
            try {
                Runnable take = workQueue.take();
                take.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }
        }
    }


}
