import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int pos = -1;
        for ( int position = 0; position < text.length(); ++position) {
             char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next,position+1));
            }

            if (next == ')' || next == ']' || next == '}') {
                if(opening_brackets_stack.isEmpty()) {
                    pos = position+1;
                    break;
                }
                // Process closing bracket, write your code here
                Bracket b = opening_brackets_stack.pop();
                if(!b.Match(next)){
                   pos = position+1;
                    break;
                }
            }
        }
        if(pos!=-1){
            System.out.println(pos);
        } else
        if(!opening_brackets_stack.isEmpty()) {
            System.out.println(opening_brackets_stack.get(0).position);
        }else
            System.out.println("Success");

        // Printing answer, write your code here
    }
}
