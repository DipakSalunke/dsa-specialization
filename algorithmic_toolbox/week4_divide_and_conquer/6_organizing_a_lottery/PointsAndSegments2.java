import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PointsAndSegments2 {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int s = starts.length;
        int p = points.length;
        ArrayList<int[]> pts = new ArrayList<>(),
                seg = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            pts.add(new int[]{points[i], i});
        }
        for (int i = 0; i < s; i++) {
            seg.add(new int[]{starts[i], 1});
            seg.add(new int[]{ends[i], -1});
        }
        seg.sort(Comparator.comparingInt(a -> a[0]));
        pts.sort(Comparator.comparingInt(a -> a[0]));
        int count = 0;
        int[] ans = new int[p];
        for (int i = 0; i < p; i++) {
            int x = pts.get(i)[0];
            while (!seg.isEmpty() &&
                    seg.get(seg.size() - 1)[0] <= x) {
                count += seg.get(seg.size() - 1)[1];
                seg.remove(seg.size() - 1);
            }
            ans[pts.get(i)[1]] = count;
        }
        return ans;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    static class Type implements Comparable<Type> {
        Integer type;
        Integer value;

        public Type(int type, int value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public int compareTo(Type o) {
            return this.value.compareTo(o.value);
        }
    }
}