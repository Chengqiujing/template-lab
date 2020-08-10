package train.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaStructure {
    public void collecionStructure(){
        List<String> list = Collections.nCopies(3, "defalut");//产生一个以 default为值，大小为3的list
        System.out.println(list);
    }

    public static void main(String[] args) {
        JavaStructure javaStructure = new JavaStructure();
        javaStructure.collecionStructure();

        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objects.add(""+i);
        }
        //截取字符串是不可操作的，要想操作需：
        /*
        List list = new ArrayList(sublist);
         */
        objects.subList(3,6);
        System.out.println(objects);


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] objects1 = list.toArray();
        String[] strings = list.toArray(new String[list.size()]);//报错 不知道为什么
        System.out.println(Arrays.toString(objects1));
        System.out.println(Arrays.toString(strings));
    }
}
