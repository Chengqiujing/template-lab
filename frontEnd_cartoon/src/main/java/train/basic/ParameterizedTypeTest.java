package train.basic;

import java.util.Collection;
import java.util.List;

public class ParameterizedTypeTest {

    public static void main(String[] args) {

    }

    /**
     * 对于泛型方法用通配符和指定泛型时哪个可以进行添加
     *
     * 记住口诀：
     *  协变（extends）只出不进
     *  逆变（super）可出可进  但进入的为泛型子类，类型不确定 仍然不可创造性的用具体子类去添加
     *
     *  总之 泛型不能和具体类型挂钩
     *
     */
//    public <T super String> T fun1(List<T> ts){
//        ts.add("");
//        return ts.get(1);
//    }

    //这两种情况是同一种表示方式
    public <T extends ParameterizedTypeTest> void fun2(List<T> ts){}
    public void fun3(List<? extends ParameterizedTypeTest> ts){}
    //下面这一种是和第一种情况一样但是有泛型返回值 此时更建议使用这种 而不用通配符
//        public <T extends ParameterizedTypeTest> T fun22(List<T> ts){
//            return ts.get(0);
//        }
}
