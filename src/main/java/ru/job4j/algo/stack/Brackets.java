package ru.job4j.algo.stack;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if ((c == ')' && open != '(')
                        || (c == '}' && open != '{')
                        || (c == ']' && open != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
