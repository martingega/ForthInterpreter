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
        operators.put("dup", () -> stack.push(stack.peek()));
    }

    @Override
    public void input(String program) {
        stack.clear();
        if(program == null) return;
        parseAndExecute(program);
    }

    private void parseAndExecute(String program) {
        try(Scanner sc = new Scanner(program)) {
            while (sc.hasNext()) {
                String token = sc.next();
                if (token.matches("-?[0-9]+"))
                    stack.push(Integer.valueOf(token));
                else if (operators.containsKey(token)) {
                    try {
                        operators.get(token).op();
                    } catch (NoSuchElementException e) {
                        throw new IllegalArgumentException("Stack Underflow");
                    }
                } else if(":".equals(token)){
                    String nome = sc.next();
                    String definizione = getDefinition(sc);
                    operators.put(nome, () -> parseAndExecute(definizione));
                } else {
                        throw new IllegalArgumentException("Token error '" + token + "'");
                }
            }
        }
    }

    private String getDefinition(Scanner sc) {
        String def = sc.findInLine(".*?;");
        return def.substring(0, def.length()-1);
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
