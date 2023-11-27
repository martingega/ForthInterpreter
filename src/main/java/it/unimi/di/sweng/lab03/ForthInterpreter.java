package it.unimi.di.sweng.lab03;

import java.util.ArrayDeque;
import java.util.Deque;

public class ForthInterpreter implements Interpreter {

    private final Deque<Integer> internalStack = new ArrayDeque<>();

    @Override
    public void input( String program) {
    }

    @Override
    public String toString(){
        if(internalStack.isEmpty()){
            return "<- Top";
        }else
            throw new IllegalStateException("Stato che non ci aspettiamo");
    }

}
