package lesson2_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    NumberComparator compTest = new NumberComparator();
    @DisplayName("Сравнение двух чисел")
    @ParameterizedTest
    @CsvSource({
            "6, 6, 'Числа равны'",
            "1, 20, '1 меньше 20'",
            "-1, 0, '-1 меньше 0'",
            "100, 5, '100 больше 5'"
    })
    void testCompare(int a, int b, String expected) {
        assertEquals(expected, compTest.compare(a, b));
    }

}