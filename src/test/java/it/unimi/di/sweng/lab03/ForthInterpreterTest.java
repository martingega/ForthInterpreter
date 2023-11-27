package it.unimi.di.sweng.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({"1 , 1 <- Top",
            "1 2, 1 2 <- Top",
            "'1\n2',1 2 <- Top",
            "'1   2 \n3',1 2 3 <- Top"})
    void testNumericInput(String program, String output){
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({"1 2 +, 3 <- Top",
            "1 2 + 5 +, 8 <- Top"})
    void testOperator(String program, String output){
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

}