import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.list;
import static java.util.Collections.min;

class EditDistance {
    public static int EditDistance(String s, String t) {
        //write your code here
        int[][] d = new int[s.length()+1][t.length()+1];
        for (int i = 0; i < s.length(); i++) {
            d[i][0] = i;
        }
        for (int i = 0; i < t.length(); i++) {
            d[0][i] = i;
        }
        for (int j = 1; j < t.length()+1; j++) {
            for (int i = 1; i < s.length()+1; i++) {
                int ins = d[i][j - 1] + 1;
                int del = d[i - 1][j] + 1;
                int mat = d[i - 1][j - 1];
                int mis = d[i - 1][j - 1] + 1;
                if(s.charAt(i-1)==t.charAt(j-1)){
                    d[i][j] = min(List.of(ins, del, mat));
                }else{
                    d[i][j] = min(List.of(ins, del, mis));
                }
            }
        }
        return d[s.length()][t.length()];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
