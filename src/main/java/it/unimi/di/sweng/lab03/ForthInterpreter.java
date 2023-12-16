package it.unimi.di.sweng.lab03;

import java.util.*;

public class ForthInterpreter implements Interpreter {

    final Deque<Integer> stack = new ArrayDeque<>();
    final Map<String, Operation> operators = new HashMap<>();

    interface Operation{
        void op();
    }

    public ForthInterpreter(){
        operators.put("+", () -> stack.push(stack.pop() + stack.pop()));
        operators.put("*", () -> stack.push(stack.pop() * stack.pop()));
    }

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
        else if (operators.containsKey(token)) {
            operators.get(token).op();
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
