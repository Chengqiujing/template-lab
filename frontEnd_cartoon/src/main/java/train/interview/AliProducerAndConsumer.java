package train.interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AliProducerAndConsumer {
    public static void main(String[] args) {
        Lock l = new ReentrantLock();
        Condition condition1 = l.newCondition();
        Condition condition2 = l.newCondition();
        Condition condition3 = l.newCondition();

        Queue<Integer> queue = new LinkedList();
        AtomicBoolean end = new AtomicBoolean(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Integer i = 1;
                Random random = new Random(123);
                while (i<=100){
                    queue.offer(i);
                    i++;
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                end.set(true);
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                l.lock();
                while(!end.get()){
                    Integer a = queue.peek();
                    if(a == null){
                        continue;
                    }
                    if(a%3 == 0){
                        System.out.println("线程A:"+queue.poll());
                    }else if(a%5 == 0){
                        condition2.signal();
                        try {
                            condition1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        condition3.signal();
                        try {
                            condition1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("线程A结束");
                condition2.signalAll();
                condition3.signalAll();
                l.unlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                l.lock();
                while(!end.get()){
                    Integer a = queue.peek();
                    if(a == null){
                        continue;
                    }
                    if(a%3 == 0){
                        condition1.signal();
                        try {
                            condition2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else if(a%5 == 0){
                        System.out.println("线程B:"+queue.poll());
                    }else{
                        condition3.signal();
                        try {
                            condition2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                condition1.signalAll();
                condition3.signalAll();
                System.out.println("线程B结束");
                l.unlock();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                l.lock();
                while(!end.get()){
                    Integer a = queue.peek();
                    if(a == null){
                        continue;
                    }
                    if(a%3 == 0){
                        condition1.signal();
                        try {
                            condition3.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else if(a%5 == 0){
                        condition2.signal();
                        try {
                            condition3.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else{
                        System.out.println("线程C:"+queue.poll());
                    }
                }
                System.out.println("线程C结束");
                condition1.signalAll();
                condition2.signalAll();
                l.unlock();
            }
        });

        thread.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
