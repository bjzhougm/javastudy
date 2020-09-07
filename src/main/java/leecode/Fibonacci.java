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

    public int fib(int n) {
        if(n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }


}
