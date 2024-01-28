import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        MVP[] arr = new MVP[values.length];
        for (int i = 0; i < values.length; i++) {
            arr[i] = new MVP(values[i] / (double) weights[i], i);
        }
        Arrays.sort(arr);
        for (int i = 0; i < values.length; i++) {
            if (capacity <= 0)
                return value;
            int available = Math.min(capacity, weights[arr[i].index]);
            weights[arr[i].index] -= available;
            capacity -= available;
            value += arr[i].valuePerPound * available;

        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    static class MVP implements Comparable<MVP> {
        double valuePerPound;
        int index;

        public MVP(double valuePerPound, int index) {
            this.valuePerPound = valuePerPound;
            this.index = index;
        }

        @Override
        public int compareTo(MVP o) {
            Double val = o.valuePerPound;
            return val.compareTo(this.valuePerPound);
        }
    }
}