package lesson2_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {
    @DisplayName("Сумма двух чисел")
    @ParameterizedTest
    @CsvSource({
            "10,5,15",
            "-8,6,-2",
            "50,50,100",
            "-1,1,0"
    })
    void testAdd(int a, int b, int sum) {
        assertEquals(sum, ArithmeticOperations.add(a, b));
    }

    @DisplayName("Вычитание двух чисел")
    @ParameterizedTest
    @CsvSource({
            "10,5,5",
            "-8,6,-14",
            "50,50,0",
            "-1,1,-2"
    })
    void testSubtract(int a, int b, int rez) {
        assertEquals(rez, ArithmeticOperations.subtract(a, b));
    }

    @DisplayName("Умножение двух чисел")
    @ParameterizedTest
    @CsvSource({
            "10,5,50",
            "-8,6,-48",
            "50,50,2500",
            "-1,1,-1"
    })
    void testMultiply(int a, int b, int rez) {
        assertEquals(rez, ArithmeticOperations.multiply(a, b));
    }

    @DisplayName("Деление двух чисел")
    @ParameterizedTest
    @CsvSource({
            "10,5,2",
            "-8,6,-1.3333333333333333",
            "50,50,1",
            "-1,1,-1"
    })
    void testDivide(int a, int b, double rez) {
        assertEquals(rez, ArithmeticOperations.divide(a, b));
    }

    @DisplayName("Деление на 0")
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> ArithmeticOperations.divide(13, 0));
    }
}