package it.unimi.di.sweng.lab03;

public class ExtendedForthInterpreter extends ForthInterpreter {

    public ExtendedForthInterpreter(){
        super();
        operators.put("-", () -> stack.push(-stack.pop() + stack.pop()));
        operators.put("/", () -> {
            int denominatore = stack.pop();
            int numeratore = stack.pop();
            stack.push(numeratore / denominatore);
        });
    }

}
