package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CaesarTest {

    private Caesar caesar;

    @BeforeEach
    public void setup() {
        caesar = new Caesar();
    }

    @ParameterizedTest(name = "test: \"{0}\", shift: \"{1}\", expected: \"{2}\", mode:\"{3}\"")
    @CsvSource({
            "Hello World, 3, Khoor Zruog, e",
            "Khoor Zruog, 3, Hello World, d",
            "я, 3, в, e",
            "Привет World, 5, Фхнжйч Btwqi, e",

            //"Привет Мир, 5, Хумёзй Рну, e", //WRONG from test
            "Привет Мир, 5, Фхнжйч Снх, e", //fixed, correct

    })
    void getResult(String input, int shift, String expected, char mode) {
        Assertions.assertEquals(expected, caesar.shift(input, shift, mode));
    }
}