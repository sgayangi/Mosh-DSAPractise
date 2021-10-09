package src;

import java.util.Scanner;
import java.util.Stack;

public class stackpractise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        System.out.println(StackOps.checkBalancedString(str));
    }
}

class StackOps {
    public static String reverseString(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            stack.add(c);
        }
        StringBuffer reversed = new StringBuffer();
        while (!(stack.isEmpty())) {
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }

    public static boolean checkBalancedString(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        Stack<Character> openingBrackets = new Stack<>();
        for (char c : input.toCharArray()) {
            if (isLeftBracket(c)) {
                openingBrackets.push(c);
            } else if (isRightBracket(c)) {
                if (openingBrackets.isEmpty()) {
                    return false;
                }
                char openingBracket = openingBrackets.pop();
                if (doBracketsMatch(openingBracket, c)) {
                    return false;
                }
            }
        }
        return openingBrackets.isEmpty();
    }

    private static boolean isLeftBracket(char c) {
        return c == '{' || c == '<' || c == '(' || c == '[';
    }

    private static boolean isRightBracket(char c) {
        return c == '}' || c == '>' || c == ')' || c == ']';
    }

    private static boolean doBracketsMatch(char left, char right) {
        return ((right != '}' || left == '{') && (right != '>' || left == '<') && (right != ')' && left == '(') && (right != ']' || left == '['));
    }
}
