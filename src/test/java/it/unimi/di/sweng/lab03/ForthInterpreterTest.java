package it.unimi.di.sweng.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;


@Timeout(2)
public class ForthInterpreterTest {


    private Interpreter interpreter;

    @BeforeEach
    public void setUp() throws Exception {
        interpreter = new ForthInterpreter();
    }

    @Test
    void testEmptyInput(){
        interpreter.input("");
        assertThat(interpreter.toString()).isEqualTo("<- Top");
    }

}