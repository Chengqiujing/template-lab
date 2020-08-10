package train.question;

/**
 * 可见性测试
 *
 * synchronize:可以保证变量可见性和一段代码的原子性
 * JMM关于synchronized两条可见性规定
 * *线程解锁前，必须把共享变量的最新值刷新到主内存中
 * *线程加锁时，将清空工作内存中共享变量的值，从而使用
 *
 */
public class ThreadSafeCahe {
    int result;
    public int getResult(){
        return result;
    }

    public void setResult(int result){
        this.result = result;
    }

    public static void main(String[] args) {
        ThreadSafeCahe threadSafeCahe = new ThreadSafeCahe();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int x = 0;
                while(threadSafeCahe.getResult() < 100){
                    x++;
                    System.out.println(x);//println方法是个同步方法
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadSafeCahe.setResult(200);
    }
}
