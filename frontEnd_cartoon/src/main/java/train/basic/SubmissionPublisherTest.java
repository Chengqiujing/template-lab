package train.basic;


import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * 基于 JDK 11
 */
public class SubmissionPublisherTest {
    public static void main(String[] args) {
        //创建一个发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        //创建订阅者
        MySuscriber<String> mySuscriber = new MySuscriber<>();
        //注册订阅者
        publisher.subscribe(mySuscriber);

        //发布几个数据项测试一下
        System.out.println("开始发布数据项");
        List.of("Java","Kotlin","Go","Erlang","Swift","Lua").forEach(im -> {
            //提交数据
            publisher.submit(im);
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publisher.submit("最后一个");
        //发布结束
        publisher.close();
        //发布以后，为了让发布者线程不会死，暂停线程
        synchronized("fkjava"){
            try {
                "fkjava".wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
class MySuscriber<T> implements Flow.Subscriber<T>{
    //发布者与订阅者之间的纽带
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //请求数据
        subscription.request(1);
    }

    @Override  //接受到数据时触发
    public void onNext(T item) {
        System.out.println("获取到数据: " + item );
        //请求下一条数据
        subscription.request(1);
    }

    @Override  //订阅出错时触发
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        synchronized("fkjava"){//不懂
            "fkjava".notifyAll();
        }
    }

    @Override  //订阅完成时触发
    public void onComplete() {
        System.out.println("订阅结束");
        synchronized ("fkjava"){//不懂
            "fkjava".notifyAll();
        }
    }
}