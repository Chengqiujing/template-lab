package train.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ClockFormat {
    /*public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int arr[] = { 1, 2 , 3, 4};
        fullSort(arr, 0 ,arr.length-1);
        System.out.println();
    }
    public static void fullSort(int[] arr, int start, int end)
    {
        if(start == end)
        {
            for(int i : arr)
                System.out.print(i + " ");
            System.out.println();
            return;
        }
        for(int i = start; i <= end; i++)
        {
            swap(arr, i , start);
            fullSort(arr, start + 1 , end);
            swap(arr, start, i);
        }
    }

    public static void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }*/
    public static void main(String[] args) {
        int[] six = {9,9,9,9,9,9};
        List<String> list = new ArrayList<>();
        ClockFormat.fullSort(six,0,six.length -1 ,list);
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            if(!s.matches("([0-1][0-9][0-5][0-9][0-5][0-9])|(2[0-3][0-5][0-9][0-5][0-9])")){
                iterator.remove();
            }
        }
        List<Integer> result = new ArrayList<>();
        if(list.size() == 0){
            System.out.println("不符合规则");
            return;
        }
        for (String s : list) {
            result.add(Integer.valueOf(s));
        }
        Collections.sort(result);
        System.out.println("最早时间为：" + result.get(0));
        System.out.println("最晚时间为：" + result.get(result.size()-1));
    }
    static void fullSort(int[] arr,int start,int end,List<String> list){
        if(start == end){
            String str = "";
            for (int i : arr) {
                str += i;
            }
            list.add(str);
            return;
        }
        for(int a = start;a <= end; a++){
            swap(arr,a,start);
            fullSort(arr,start+1,end,list);
            swap(arr,a,start);
        }
    }


    static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }




//    //处理24小时格式
//    String min(int[] ints){
//        Arrays.sort(ints);
//        int hh = ints[0]*10 + ints[1];
//        if(ints[4] <= 5){
//            return "" + hh + ints[2] + ints[3] + ints[4] + ints[5];
//        }
//        return "" + hh + ints[2] + ints[4] + ints[3] + ints[5];
//    }
//
//    String max(int[] ints){
//        Arrays.sort(ints);
//        int i = 0;
//        while(ints[i] <= 2){
//            i++;
//        }
//        String h1 = "";
//        if(ints[i] == 0){
//            h1 = ints[5] + "" + ints[0];
////            while(){
////
////            }
//        }else if(ints[i] == 1){
//
//        }else if(ints[i] == 2){
//
//        }
//        return "";
//    }
//
//    boolean isFormat(int[] ints){
//        Arrays.sort(ints);
//        if(ints[0] == 0 || ints[0] == 1){
//            if(ints[1] < 6 && ints[2] < 6){
//                return true;
//            }
//        }else if(ints[0] == 2 && ints[1] < 3){
//            if(ints[2] < 6 && ints[3] < 6){
//                return true;
//            }
//        }
//        return false;
//    }

}
