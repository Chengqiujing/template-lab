package train.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("1");
        System.out.println(list.containsAll(list1));

        list.add("10");
        list.add("11");
        list.add("12");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("11"))
            iterator.remove();
        }
        System.out.println(list);
    }
}
