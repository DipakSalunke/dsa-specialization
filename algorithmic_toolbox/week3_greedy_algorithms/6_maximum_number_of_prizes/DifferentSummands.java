import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {

        ArrayList<Integer> summands = new ArrayList<Integer>();
        //write your code here
        int sum=0;
        int index = 1;
      while (sum<n){
          sum+=index;
          summands.add(index);
          index++;
      }
      if(sum-n-1>=0)
      summands.remove(sum-n-1);
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

