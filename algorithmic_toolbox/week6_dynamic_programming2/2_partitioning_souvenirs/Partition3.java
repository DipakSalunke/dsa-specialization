import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

public class Partition3 {
    public static void reverse(int[] array) {

        // Length of the array
        int n = array.length;

        // Swapping the first half elements with last half
        // elements
        for (int i = 0; i < n / 2; i++) {

            // Storing the first half elements temporarily
            int temp = array[i];

            // Assigning the first half to the last half
            array[i] = array[n - i - 1];

            // Assigning the last half to the first half
            array[n - i - 1] = temp;
        }
    }

    private static int partition3(int[] wt) {
        //write your code here
        Arrays.sort(wt);
        reverse(wt);
        int sum = Arrays.stream(wt).sum();
        if (sum % 3 != 0)
            return 0;
        int W = sum / 3;

        int i, w;
        int[][] K = new int[wt.length + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= wt.length; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w]
                            = max(wt[i - 1]
                                    + K[i - 1][w - wt[i - 1]],
                            K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        int t = wt.length;
        int s = W;
        while (t != 0 && s != 0) {
            if (K[t][s] == K[t - 1][s - 1]) {
                t--;
            } else {
                t = t - 1;
                s = s - wt[t - 1];
                System.out.println(wt[t]);
            }
        }
        System.out.println(wt[t]);
        return K[wt.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

