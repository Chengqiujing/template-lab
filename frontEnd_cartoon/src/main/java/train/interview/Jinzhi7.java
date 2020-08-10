package train.interview;

import java.util.Stack;

public class Jinzhi7 {
    public static void main(String[] args) {
        Jinzhi7 j = new Jinzhi7();
        System.out.println(j.convertToBase7(100));
    }
    public String convertToBase7(int num) {
        Stack<Integer> s = new Stack();
        int left = num;
        while(left > 7){
            int a = left % 7;
            s.push(a);
            left = (left -a) /7;
        }
        s.push(left);
        String result = "";
        while(!s.empty()){
            result += s.pop();
        }
        return result;
    }
}
