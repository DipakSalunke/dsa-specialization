import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();

        int[] memo = new int[n + 1];
        for (int i = 2; i < 4; i++) {
            if (i <= n)
                memo[i] = 1;
        }
        for (int i = 4; i <= n; i++) {
            int[] minReq = new int[3];
            Arrays.fill(minReq, Integer.MAX_VALUE);
                minReq[0] = memo[i - 1] + 1;
            if (i % 2 == 0) {
                minReq[1] = memo[i / 2] + 1;
            }
            if (i % 3 == 0) {
                minReq[2] = memo[i / 3] + 1;
            }
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                if (minReq[j] < min) {
                    min = minReq[j];
                }
            }
            memo[i] = min;
        }
        int num = n;
        sequence.add(n);
        while(num>1){
            int minReq = memo[num];
            if(memo[num-1]==minReq-1){
                num = num-1;
                sequence.add(num);
            }else
            if(num%2 == 0 && memo[num/2]==minReq-1){
                num = num/2;
                sequence.add(num);
            }else
            if(num%3 == 0 && memo[num/3]==minReq-1){
                num = num/3;
                sequence.add(num);
            }
        }
//        int num1 = n;
//        while (num1>1){
//            if(num1%3==0){
//                num1 = num1/3;
//                System.out.print(num1+" ");
//            } else if(num1%2==0){
//                num1 = num1/2;
//                System.out.print(num1+" ");
//            }else{
//                num1 = num1-1;
//                System.out.print(num1+" ");
//            }
//
//        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

