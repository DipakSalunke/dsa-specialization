import java.util.Scanner;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        //first stop reachable check
        if (stops[0] > tank) return -1;

        int currentRange = tank;
        int refills = 0;
        for (int i = 0; i < stops.length - 1; i++) {
            if (stops[i + 1] > currentRange) {
                currentRange = stops[i] + tank;
                if (currentRange < stops[i + 1]) {
                    return -1;
                } else
                    refills++;
            }
        }

        //is destination reachable check
        if (dist > currentRange) {
            currentRange += tank;
            if (currentRange < dist) {
                return -1;
            } else
                refills++;
        }
        return refills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
