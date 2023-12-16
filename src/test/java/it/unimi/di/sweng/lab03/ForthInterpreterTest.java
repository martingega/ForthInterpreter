package it.unimi.di.sweng.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Timeout(2)
public class ForthInterpreterTest {

    private Interpreter interpreter;

    @BeforeEach
    public void setUp() throws Exception {
        interpreter = new ForthInterpreter();
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
    @NullSource
    @EmptySource
    void testEmptyOrNull(String program){
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo("<- Top");
    }

    @ParameterizedTest
    @CsvSource({"1 2 +, 3 <- Top",
            "1 2 + 5 +, 8 <- Top",
            "1 2 *, 2 <- Top",
            "1 2 * 5 *, 10 <- Top"})
    void testOperator(String program, String output){
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    // testare le eccezioni
    @ParameterizedTest
    @CsvSource({"1 2+, 2+",
            "1 2 ++5 +, ++5"})
    void testInvalidToken(String program, String error){
        assertThatThrownBy(() -> interpreter.input(program))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Token error '" + error + "'");
    }

    // definisco nuova classe capace a gestire anche il 'meno' e 'diviso'
    // mentre ForthInterpreter rimane a gestire solo il 'più' e il 'per'
    @ParameterizedTest
    @CsvSource({"1 2 -, -1 <- Top",
            "1 2 /, 0 <- Top",
            "1 2 3 dup, 1 2 3 3 <- Top"})
    void testOtherOperator(String program, String output){
        Interpreter interpreter = new ExtendedForthInterpreter();
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 +"})
    void testStackUnderflow(String program){
        assertThatThrownBy(() -> interpreter.input(program))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Stack Underflow");
    }

}