import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        StringBuilder result = new StringBuilder();
        Arrays.sort(a, (o1, o2) -> {
           int min = Math.min(o1.length(), o2.length());
           int val =  o2.substring(0,min).compareTo(o1.substring(0,min));
           if(val ==0)
               return o1.length()-o2.length();
           else return val;
        });

        for (String s : a) {
            result.append(s);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

