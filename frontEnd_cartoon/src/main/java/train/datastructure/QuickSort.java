package train.datastructure;

import java.util.Arrays;

public class QuickSort {
    //最简单的递归实现


    /**
     * 递归思想实现,需要
     * 1.选一个基准,一般选最左边一个
     * 2.先看右边,如果大于基准位则左移,直到小于基准位
     * 3.再看左边,如果小于基准位则右移,直到大于基准位
     * 4.如果左计数未与右计数未相遇,则交换两个值, 这样直到相遇时,相遇值左边的会小于基准值,右边的会大于基准值
     * 5.将基准值,换至相遇处作为分割
     * 6.然后分为2部分再次递归循环.然后再分4部分,然后
     *
     *
     *
     *
     * @param arr
     * @param low
     * @param high
     */

    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }

            //如果满足条件则交换
            System.out.println(arr[i] + "和" + arr[j] + "交换--->" + Arrays.toString(arr));
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


    public static void main(String[] args){
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }


}
