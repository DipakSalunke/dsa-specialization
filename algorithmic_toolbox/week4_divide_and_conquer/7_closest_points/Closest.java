import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {
   static class Point {
        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static float dist(Point p1, Point p2) {
            return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                    (p1.y - p2.y) * (p1.y - p2.y)
            );
        }

        public static float bruteForce(Point[] P, int n) {
            float min = Float.MAX_VALUE;
            float currMin = 0;

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    currMin = dist(P[i], P[j]);
                    if (currMin < min) {
                        min = currMin;
                    }
                }
            }
            return min;
        }

        public static float stripClosest(Point[] strip, int size, float d) {
            float min = d;

            Arrays.sort(strip, 0, size, new PointYComparator());

            for (int i = 0; i < size; ++i) {
                for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                    if (dist(strip[i], strip[j]) < min) {
                        min = dist(strip[i], strip[j]);
                    }
                }
            }

            return min;
        }

        public static float closestUtil(Point[] P,
                                        int startIndex,
                                        int endIndex) {

            if ((endIndex - startIndex) <= 3) {
                return bruteForce(P, endIndex);
            }

            int mid = startIndex + (endIndex - startIndex) / 2;
            Point midPoint = P[mid];


            float dl = closestUtil(P, startIndex, mid);
            float dr = closestUtil(P, mid, endIndex);

            float d = Math.min(dl, dr);

            Point[] strip = new Point[endIndex];
            int j = 0;
            for (int i = 0; i < endIndex; i++) {
                if (Math.abs(P[i].x - midPoint.x) < d) {
                    strip[j] = P[i];
                    j++;
                }
            }

            return Math.min(d, stripClosest(strip, j, d));
        }

        public static float minimalDistance(int [] x, int[] y) {
             Point[] P = new Point[x.length];
            for (int i = 0; i < x.length; i++) {
                P[i] = new Point(x[i],y[i]);
            }
            Arrays.sort(P, 0, P.length, new PointXComparator());
            return closestUtil(P, 0, P.length);
        }


   static class PointXComparator implements Comparator<Point> {
        @Override
        public int compare(Point pointA, Point pointB) {
            return Integer.compare(pointA.x, pointB.x);
        }

    }

    static class PointYComparator implements Comparator<Point> {
        @Override
        public int compare(Point pointA, Point pointB) {
            return Integer.compare(pointA.y, pointB.y);
        }
    }
}
    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(Point.minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
