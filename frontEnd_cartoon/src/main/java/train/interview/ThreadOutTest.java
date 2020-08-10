package train.interview;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 两个线程A，B按序输出0-100间的元素，线程A只负责输出质数，线程B负责输出其他元素，
 */
public class ThreadOutTest {
    static boolean isZhi(int num){
        if(num == 1 || num  == 2){
            return  true;
        }
        for(int i=2;i<num;i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        //第一种方式 使用原子类
        AtomicInteger c = new AtomicInteger(0);
        Thread a  = new Thread(new Runnable() {
            @Override
            public void run() {
                while(c.get() <=100){
                    if(isZhi(c.get())){
                        System.out.println("A-质数：" + c.get());
                        c.set(c.get() + 1);
                    }else{
                        synchronized (c){
                            c.notifyAll();
                            try {
                                c.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                synchronized (c){
                    c.notifyAll();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while(c.get() <=100){
                    if(isZhi(c.get())){
                        synchronized (c){
                            c.notifyAll();
                            try {
                                c.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        System.out.println("B-非质数："+c.get());
                        c.set(c.get() + 1);
                    }
                }
                synchronized (c){
                    c.notifyAll();
                }
            }
        });
        a.start();
        b.start();


    }
}
