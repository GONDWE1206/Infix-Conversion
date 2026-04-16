import java.util.Stack;

public class InfixConversion {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Function to check if operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // Operand
            if (isOperand(ch)) {
                result += ch;
            }

            // Left parenthesis
            else if (ch == '(') {
                stack.push(ch);
            }

            // Right parenthesis
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop(); // remove '('
            }

            // Operator
            else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        // Step 1: Reverse
        exp = reverse(exp);

        // Step 2: Replace brackets
        char[] ch = exp.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                ch[i] = ')';
            } else if (ch[i] == ')') {
                ch[i] = '(';
            }
        }

        // Step 3: Convert to postfix
        String postfix = infixToPostfix(String.valueOf(ch));

        // Step 4: Reverse postfix
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String infix = "(A+B)*C";

        System.out.println("Infix: " + infix);

        String postfix = infixToPostfix(infix);
        System.out.println("Postfix: " + postfix);

        String prefix = infixToPrefix(infix);
        System.out.println("Prefix: " + prefix);
    }
}