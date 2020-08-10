package train.interview;

public class LongestReverseString {
    public int longestPalindromeSubseq(String s) {
        int length = 0;
        String subString = "";
        for(int i=0;i<s.length();i++){
            char front = s.charAt(i);
            for(int j=i+1;j<s.length();j++){
                if(front == s.charAt(j)&& isHuiWen(s.substring(i,j))){
                    if(j-i+1 > length){
                        length = j-i+1;
                        subString = s.substring(i,j);
                    }
                }
            }
            if(s.length()-i < length){
                break;
            }
        }
        System.out.print(subString);
        return length;
    }

    static boolean isHuiWen(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        if(str.equals(sb.toString())){
            return true;
        }
        return false;
    }
}
