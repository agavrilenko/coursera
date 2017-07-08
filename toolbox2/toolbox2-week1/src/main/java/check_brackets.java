import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        System.out.println(processString(text));
    }

    public static String processString(String text) {
        Stack<Bracket> bracketStack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                bracketStack.add(new Bracket(next, position + 1));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (!bracketStack.isEmpty()) {
                    Bracket bracket = bracketStack.pop();
                    if (!bracket.Match(next)) {
                        return String.valueOf(position + 1);
                    }
                } else {
                    return String.valueOf(position + 1);
                }
            }
        }

        // Printing answer, write your code here
        return bracketStack.isEmpty() ? "Success" : String.valueOf(bracketStack.elementAt(0).position);
    }
}
