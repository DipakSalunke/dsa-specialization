import java.util.Arrays;
import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] memo = new int[m + 1];
        int[] coins = {1, 3, 4};
        for (int i = 0; i < coins.length; i++) {
            if (m >= coins[i])
                memo[coins[i]] = 1;
        }
        for (int i = 1; i <= m; i++) {
            int[] minReq = new int[coins.length];
            Arrays.fill(minReq, Integer.MAX_VALUE);
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    minReq[j] = memo[i - coins[j]] + 1;
                }else break;
            }
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < minReq.length; j++) {
                if (minReq[j] < min)
                    min = minReq[j];
            }
            memo[i] = min;
        }
        return memo[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

