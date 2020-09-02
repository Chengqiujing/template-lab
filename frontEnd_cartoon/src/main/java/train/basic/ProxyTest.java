package train.basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 本测试证明 Mybatis的Mapper只写接口，而不写实现的基于代理的核心内容
 */
public class ProxyTest {
    public static void main(String[] args) {
        Dog dog = Factory.<Dog>toProxy();
        dog.eat();
        //System.out.println(Dog.class.getClasses().length);
    }
}
interface Dog{
    void run();
    void eat();
}


/**
 * (2) 1代的升级形式,不再指定类型,由泛型指定
 */
class Factory{
    public static <T> T toProxy(){
        T o = (T) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Dog.class}, new proxyImpl());
        return o;
    }
}

/**
 * (1) 固定类型代理
 */
//class Factory{
//    public static void toProxy(){
//
//        Dog o = (Dog) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Dog.class}, new proxyImpl());
//        o.run();
//    }
//}
class proxyImpl implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("test");
        return null;
    }
}