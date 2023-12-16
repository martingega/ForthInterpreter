package it.unimi.di.sweng.lab03;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ForthInterpreter implements Interpreter {

    private final Deque<Integer> stack = new ArrayDeque<>();

    @Override
    public void input( String program) {
        try(Scanner sc = new Scanner(program)) {
            while (sc.hasNext()) {
                String token = sc.next();
                parseAndExecute(token);
            }
        }
    }

    private void parseAndExecute(String token) {
        if (token.matches("-?[0-9]+"))
            stack.push(Integer.valueOf(token));
        else if ("+".equals(token)) {
            stack.push(stack.pop() + stack.pop());
        } else if ("*".equals(token)) {
            stack.push(stack.pop() * stack.pop());
        } else {
            throw new IllegalArgumentException("Token error '" + token + "'");
        }
    }

    @Override
    public String toString(){
            StringBuilder sb = new StringBuilder();
            for (Integer operand : stack){
                sb.insert(0, operand + " ");
            }
            sb.append("<- Top");
            return sb.toString();
    }

}
