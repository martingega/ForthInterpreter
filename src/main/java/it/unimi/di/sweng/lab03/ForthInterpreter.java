package it.unimi.di.sweng.lab03;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ForthInterpreter implements Interpreter {

    private final Deque<Integer> internalStack = new ArrayDeque<>();

    @Override
    public void input( String program) {
        try(Scanner sc = new Scanner(program)) {
            while (sc.hasNext()) {
                String token = sc.next();
                internalStack.push(Integer.valueOf(token));
            }
        }
    }

    @Override
    public String toString(){
            StringBuilder sb = new StringBuilder();
            for (Integer operand : internalStack){
                sb.insert(0, operand + " ");
            }
            sb.append("<- Top");
            return sb.toString();
    }

}
