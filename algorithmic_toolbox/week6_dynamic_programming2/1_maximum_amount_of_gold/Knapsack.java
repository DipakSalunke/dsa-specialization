import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Knapsack {
    static int optimalWeight(int W, int[] wt) {
        // Making and initializing dp array
        int[] dp = new int[W + 1];

        for (int i = 0; i < wt.length; i++) {
            for (int w = W; w >= 0; w--) {

                if (wt[i] <= w)

                    // Finding the maximum value
                    dp[w]
                            = Math.max(dp[w], dp[w - wt[i]]
                            + wt[i]); //wt as value
            }
            System.out.println(Arrays.toString(dp));
        }
        // Returning the maximum value of knapsack
        return dp[W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

