import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.min;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        return extracted(extracted(a, b), c).length;
    }

    private static int[] extracted(int[] a, int[] b) {
        ArrayList<Integer> subSeq = new ArrayList<>();
        int[][] d = new int[a.length + 1][b.length + 1];
        for (int i = 0; i < a.length+1; i++) {
            d[i][0] = i;
        }
        for (int i = 0; i < b.length+1; i++) {
            d[0][i] = i;
        }
        for (int j = 1; j < b.length + 1; j++) {
            for (int i = 1; i < a.length + 1; i++) {
                int ins = d[i][j - 1] + 1;
                int del = d[i - 1][j] + 1;
                int mat = d[i - 1][j - 1];
                int mis = d[i - 1][j - 1] + 1;
                if (a[i - 1] == b[j - 1]) {
                    d[i][j] = min(List.of(ins, del, mat));
                } else {
                    d[i][j] = min(List.of(ins, del, mis));
                }
            }
        }
        int i = a.length;
        int j = b.length;
        while (i != 0 && j != 0) {
            int ins = d[i][j - 1];
            int del = d[i - 1][j];
            int mat = d[i - 1][j - 1];
            int min = min(List.of(ins, del, mat));
            if (mat == min || d[i][j] == 1) {
                if (mat == d[i][j] || d[i][j] == 1 )
                    subSeq.add(a[i - 1]);
                i = i - 1;
                j = j - 1;
            } else if (min == ins) {
                j = j - 1;
            } else if (min == del) {
                i = i - 1;
            }
        }
        Collections.reverse(subSeq);
        return subSeq.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

