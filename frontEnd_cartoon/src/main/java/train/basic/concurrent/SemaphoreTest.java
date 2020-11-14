package train.basic.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author chengqj
 * @Date 2020/11/14 14:52
 * @Desc
 */
public class SemaphoreTest {
    /**
     * Semaphore 信号量
     * 1. 保护共享资源的访问
     * 2. 它是个计数器
     * 3. >0 可以访问 每访问一次减1
     * 4. =0 线程休眠 直至再次>0
     * 5. 信号量使用完必须释放
     * <p>
     * Semaphore 有两种构造器
     * Semaphore(int permits)
     * Semaphore(int permits, boolean fair)
     * <p>
     * permits 初始许可数，也就是最大访问线程数
     * fair 当设置为false时，创建的信号量为非公平模式；当设置为true时，信号量是公平模式
     */
    final static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire(); //
                        System.out.println("线程:" + Thread.currentThread().getName() + "获得许可:" + index);
                        TimeUnit.SECONDS.sleep(1);
                        semaphore.release();
                        System.out.println("允许TASK个数：" + semaphore.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();

    }
}
