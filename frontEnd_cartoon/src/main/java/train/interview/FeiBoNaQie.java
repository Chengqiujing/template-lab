package train.interview;

public class FeiBoNaQie {
    public int fib(int N) {
        if(N == 0){
            return 0;
        }if(N == 1){
            return 1;
        }else{
            return fib(N-1) + fib(N -2);
        }
    }

    public static void main(String[] args) {
        FeiBoNaQie f = new FeiBoNaQie();
        System.out.println(f.fib(4));
    }
}
