import java.io.*;
import java.util.*;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    private static int[] H ;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int prime = 1000000007;
        int random = new Random().nextInt(1,s.length()-1);
        preComputehashes(t,s.length(),prime,random);
        List<Integer> occurrences = new ArrayList<Integer>();
       int pHash = hashFunc(s,random,prime);
        for (int i = 0; i < t.length()-s.length()+1; i++) {
            if(pHash!=H[i]){
                continue;
            }
            if(t.substring(i, i+s.length()).equals(s))
                occurrences.add(i);
        }
        return occurrences;
    }
    private static int hashFunc(String s, int multiplier, int prime) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash;
    }
    private static void preComputehashes(String T, int P, int p, int x) {
        H = new int[T.length()-P+1];
        String S = T.substring(T.length()-P);
        H[T.length()-P] = hashFunc(S,x,p);
        int y = 1;
        for (int i = 1; i < P; i++) {
            y = y*x%p;
        }
        for (int i = T.length()-P-1; i>=0 ; i--) {
            H[i] = (x*H[i+1]+T.charAt(i)-y*T.charAt(i+P))%p;
        }
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

