package leecode;

/**
 * N阶梯问题
 */
public class Fibonacci {

    public static int compute(int n){
        if (n ==1)
            return 1;
        if (n ==2)
            return 2;
        return compute(n-1) + compute(n-2);
    }
}
