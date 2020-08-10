package train.basic;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinTest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new Printtask(0,300));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}
class Printtask extends RecursiveAction {
    private static final int LIMIT = 50;
    private int start;
    private int end;

    public Printtask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < LIMIT){
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName()+"的i值" + i);
            }
        }else{
            int middle = (start + end) / 2;
            Printtask printtask = new Printtask(start, middle);
            Printtask printtask1 = new Printtask(middle, end);
            printtask.fork();
            printtask1.fork();
        }
    }
}