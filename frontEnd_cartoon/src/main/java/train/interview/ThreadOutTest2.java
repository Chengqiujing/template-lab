package train.interview;


/**
 * 两个线程A，B按序输出0-100间的元素，线程A只负责输出质数，线程B负责输出其他元素
 */
public class ThreadOutTest2 {
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

        //通过同步方法保持可见性
        final int[] count = {0};
        Thread a  = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (count){
                    while(count[0] <=100){
                        if(isZhi(count[0])){
                            System.out.println("A-质数：" + count[0]);

                            count[0] += 1;
                        }else{
                            count.notifyAll();
                            try {
                                count.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    count.notifyAll();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (count) {
                    while (count[0] <= 100) {
                        if (isZhi(count[0])) {
                            count.notifyAll();
                            try {
                                count.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("B-非质数：" + count[0]);
                            count[0]++;
                        }
                    }
                    count.notifyAll();
                }

            }
        });
        a.start();
        b.start();


    }
}
