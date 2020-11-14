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

    public static void main(String[] args) {
        // pringABC_Synchronize();
        pringABC_Lock();
        // pringABC_Semaphore();
        // pringABC_AtomicInteger();
    }

    private static void pringABC_Synchronize() {
        executorService.execute(new SynchronizeWork(0, "A"));
        executorService.execute(new SynchronizeWork(1, "B"));
        executorService.execute(new SynchronizeWork(2, "C"));
        executorService.shutdown();
    }

    private static void LockMethod(Lock lock, Condition self, Condition next, int count) {
        try {
            lock.lock();
            if (count % 3 != 1) {
                self.await();
            }
            count++; // 如果线程走了就++
            while (true) {
                System.out.println('A');
                next.signal();
                self.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pringABC_Lock() {
        LockWork a = new LockWork(0, "A");
        Condition aCurrCondition = a.getCurrCondition();
        LockWork b = new LockWork(1, "B");
        Condition bCurrCondition = b.getCurrCondition();
        LockWork c = new LockWork(2, "C");
        Condition cCurrCondition = c.getCurrCondition();

        a.setNextCondition(bCurrCondition);
        b.setNextCondition(cCurrCondition);
        c.setNextCondition(aCurrCondition);

        executorService.execute(a);
        executorService.execute(b);
        executorService.execute(c);


    }

    private static void pringABC_Semaphore() {

    }

    private static void pringABC_AtomicInteger() {

    }

}

class SynchronizeWork implements Runnable {
    private final static Object lockObj = new Object();
    private static int count = 0;
    private int threadIndex;
    private String letter;

    public SynchronizeWork(int threadIndex, String letter) {
        this.threadIndex = threadIndex;
        this.letter = letter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lockObj) {
                if (count % 3 == threadIndex) {
                    count++;
                    System.out.println(letter);
                    lockObj.notifyAll();
                }
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class LockWork implements Runnable {
    private final static Lock lock = new ReentrantLock();
    private Condition currCondition = lock.newCondition();
    private static int count = 0;
    private Condition nextCondition;
    private String leter;
    private int thredIndex;

    public LockWork(int thredIndex, String leter) {
        this.leter = leter;
        this.thredIndex = thredIndex;
    }

    public Condition getCurrCondition() {
        return currCondition;
    }

    public void setNextCondition(Condition nextCondition) {
        this.nextCondition = nextCondition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                if (count % 3 == thredIndex) {
                    count++;
                    System.out.println(leter);
                    nextCondition.signal();
                }
                currCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}