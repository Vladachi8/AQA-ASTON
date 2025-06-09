package lesson2_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    @DisplayName("Факторил числа")
    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "4, 24",
            "10, 3628800"
    })
    void testFactorialValidInput(int input, long expected) {
        assertEquals(expected, Factorial.factorial(input));
    }

    @DisplayName("Проверка на отрицательные числа")
    @ParameterizedTest
    @ValueSource(ints = {-1, -7, -200})
    void testFactorialInvalidInput(int input) {
        assertThrows(IllegalArgumentException.class,
                () -> Factorial.factorial(input));
    }
}