import java.io.*;
import java.util.*;

public class BinarySearch1 {

    static int binarySearch1(int[] a, int x) {
        int left = 0, right = a.length-1;
        //write your code here
        while (left<=right){
            int mid = (left+right)/2;
            if(a[mid]==x)
                return mid;
            else if(a[mid]<x)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
        //return search(a,left, right , (left+right)/2, x);
    }

    private static int search(int[] a, int left, int right, int mid, int x) {
        if(left>right)
            return -1;
        if(a[mid]==x)
            return mid;
        else if(x<a[mid])
            return search(a, left, mid-1, (left+mid-1)/2, x);
        else
            return search(a, mid+1, right, (right+mid+1)/2, x);
    }
//5
//1 5 8 12 13
//5
//8 1 23 1 11
    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
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
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearch1(a, b[i]) + " ");
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
