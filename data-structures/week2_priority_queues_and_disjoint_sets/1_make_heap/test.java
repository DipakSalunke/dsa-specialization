public class test {
    public static void main(String[] args) {
        String s1 = "XYZXYZXYZ";
        String s2 = "XYZXYZXYZXYZXYZ";
        System.out.println(extracted(s1, s2));
    }

    private static String extracted(String bigger, String smaller) {
        if (bigger.length() < smaller.length()) {
            String temp = bigger;
            bigger = smaller;
            smaller = temp;
        }

        if (smaller.isEmpty() || !bigger.startsWith(smaller)) {
            return "";
        }
        while (!smaller.isEmpty() && bigger.startsWith(smaller)) {
            String temp = smaller;
            smaller = bigger.substring(smaller.length());
            bigger = temp;
            if (bigger.length() < smaller.length()) {
                String temp1 = bigger;
                bigger = smaller;
                smaller = temp1;
            }
        }
        if (bigger.equals(smaller) || smaller.isEmpty())
            return bigger;
        else
            return "";
    }
}
