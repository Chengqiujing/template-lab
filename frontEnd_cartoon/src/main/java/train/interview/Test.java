package train.interview;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        AtomicInteger apple = new AtomicInteger(9);

        Thread monkeyA = new Thread(new Runnable(){
            public void run(){
                synchronized(apple){
                    while(apple.get() > 2){
                        apple.set(apple.get() - 2);
                        System.out.println("猴子A拿取2个");
                    }
                    System.out.println("苹果不够猴子A拿了");
                }
            }
        });
        Thread monkeyB = new Thread(new Runnable(){
            public void run(){
                synchronized(apple){
                    while(apple.get() > 3){
                        apple.set(apple.get() - 3);
                        System.out.println("猴子B拿取3个");
                    }
                    System.out.println("苹果不够猴子B拿了");
                }
            }
        });

        monkeyA.start();
        monkeyB.start();
    }
}
