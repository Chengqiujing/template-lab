package train.interview;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chengqj
 * @Date 2020/11/14 10:08
 * @Desc 用三个线程交替打印ABC
 * 有四种实现方法:
 * 1. synchronize 本人的这种方法不可取,但能达到效果
 * 2. Lock + Condition
 * 3. Semaphore
 * 4. AtomicInteger
 * <p>
 * 这里的代码是可以重构的更好,为了问题过程更明了,我不对代码进行重构
 */
public class Thread3PrintABC {

    private final static ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static int count = 1;

    public static void main(String[] args) {
        pringABC_Synchronize();
        // pringABC_Lock();
        // pringABC_Semaphore();
        // pringABC_AtomicInteger();
    }

    private static void pringABC_Synchronize() {

    }

    private static void pringABC_Lock() {
        Lock lock = new ReentrantLock();
        Condition aa = lock.newCondition();
        Condition bb = lock.newCondition();
        Condition cc = lock.newCondition();

        // print A
        executorService.submit(() -> {
            System.out.println("threada in...");
            try {
                lock.lock();
                if (count % 3 != 1) {
                    cc.await();
                }
                count++; // 如果线程走了就++
                while (true) {
                    System.out.println('A');
                    bb.signal();
                    aa.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // print B
        executorService.submit(() -> {
            System.out.println("threadb in...");
            try {
                lock.lock();
                if (count % 3 != 2) {
                    bb.await();
                }
                count++; // 如果线程走了就++
                while (true) {
                    System.out.println('B');
                    cc.signal();
                    bb.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // print C
        executorService.submit(() -> {
            System.out.println("threadc in...");
            try {
                lock.lock();
                if (count % 3 != 0) {
                    cc.await();
                }
                count++; // 如果线程走了就++
                while (true) {
                    System.out.println('C');
                    aa.signal();
                    cc.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

    private static void pringABC_Semaphore() {

    }

    private static void pringABC_AtomicInteger() {

    }

}
