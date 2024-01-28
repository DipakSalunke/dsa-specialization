import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        Arrays.sort(segments);
        int index = 0;
        //write your code here
        int[] points = new int[segments.length];
        points[0] = segments[0].end;
        for (Segment segment : segments) {
            if (segment.start > points[index]) {
                index++;
                points[index] = segment.end;
            }
        }
        return Arrays.copyOf(points,index+1);
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(@NotNull Segment o) {
            return Integer.compare(this.end, o.end);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
