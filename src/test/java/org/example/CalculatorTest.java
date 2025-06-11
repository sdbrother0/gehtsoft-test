package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @ParameterizedTest(name = "test: \"{0}\", expected: \"{1}\"")
    @CsvSource({
            "2 + 3 * 4,             14",
            "(10 + 5) / 3,          5",
            "2 * (3 + 4) - 1,       13",
            "-5 + 3,                -2",
            "2 * ((3 + 4) - 1),     12",
            "(10 + 5) / 4,          3",
            "((1 + 2) + 7) * 3,     30",
            "3 / 0,                 Division by zero error",
            "3 / (5 - 5),           Division by zero error",
            "(1,                    Parsing input error"
    })
    void getResult(String input, String expected) {
        Assertions.assertEquals(expected, calculator.calc(input));
    }
}