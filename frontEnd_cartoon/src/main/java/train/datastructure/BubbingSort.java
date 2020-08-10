package train.datastructure;

import java.util.Arrays;

public class BubbingSort {
    //冒泡排序

    public static void main(String[] args) {
        int[] arr = {1,9,8,7,6,5,4,3,2};

        BubbingSort.bubbingSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     *
     * 注意两点:
     *  1.第一次循环的次数为 length -1 ,因为最后一个元素不需要比较
     *  2.内层循环要在 -1的基础上-i,因外外层每循环一次就选出一个最大元素,所以内层相应减少一次循环
     *
     *
     * @param arr
     */

    static void bubbingSort(int[] arr){
        if(arr.length >= 2){
            int tmp;
            for (int i = 0; i < arr.length -1; i++) {
                for (int j = 0; j < arr.length - i-1; j++) {
                    if(arr[j] > arr[j+1]){
                        tmp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = tmp;
                    }
                }
            }
        }
    }



}
