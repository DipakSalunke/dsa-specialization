import java.util.*;

import static java.util.Collections.min;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        //Write your code here
        int count = 0;
        int lastI = 0;
        int[][] d = new int[a.length+1][b.length+1];
        for (int i = 0; i < a.length; i++) {
            d[i][0] = i;
        }
        for (int i = 0; i < b.length; i++) {
            d[0][i] = i;
        }
        for (int j = 1; j < b.length+1; j++) {
            for (int i = 1; i < a.length+1; i++) {
                int ins = d[i][j - 1] + 1;
                int del = d[i - 1][j] + 1;
                int mat = d[i - 1][j - 1];
                int mis = d[i - 1][j - 1] + 1;
                if(a[i-1]==b[j-1]){
                    d[i][j] = min(List.of(ins, del, mat));
                    if(mat==d[i][j] && i>lastI) count++;
                    lastI = i;
                }else{
                    d[i][j] = min(List.of(ins, del, mis));
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

