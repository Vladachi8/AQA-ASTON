package lesson2_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {
    TriangleArea areaTest = new TriangleArea();
    @DisplayName("Площадь треугольника")
    @ParameterizedTest
    @CsvSource({
            "10,20,100",
            "1,5,2.5",
            "35,82,1435"
    })
    void testTriangleArea(double base, double height, double expectedArea) {
        double actualArea = areaTest.calculateArea(base, height);
        assertEquals(expectedArea, actualArea);
    }

    @DisplayName("Проверка на ноль или отрицательный ввод")
    @ParameterizedTest
    @CsvSource({
            "-2, 6",
            "-0.1, 20",
            "-1, 0",
            "0, 5"
    })
    void testTriangleAreaInvalidInput(double base, double height) {
        assertThrows(IllegalArgumentException.class,
                () -> areaTest.calculateArea(base, height));
    }
}