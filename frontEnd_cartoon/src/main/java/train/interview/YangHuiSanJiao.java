package train.interview;

import java.util.ArrayList;
import java.util.List;

public class YangHuiSanJiao {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        for (int i = 0; i < 10; i++) {
            for (Integer integer : l) {
                System.out.print(integer + " ");
            }
            System.out.println();
            l = line(l);
        }

    }

    static List line(List<Integer> list){
        List<Integer> tmp = new ArrayList<>();
        if(list.size() == 1){
            tmp.add(1);
            tmp.add(1);
            return tmp;
        }
        for (int i = 0; i <= list.size(); i++) {
            if(i==0){
                tmp.add(list.get(i));
                continue;
            }
            if(i==list.size() ){
                tmp.add(list.get(i-1));
                continue;
            }
            tmp.add(list.get(i)+list.get(i-1));
        }
        return tmp;
    }
}
