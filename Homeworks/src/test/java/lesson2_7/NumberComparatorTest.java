package lesson2_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberComparatorTest {
    NumberComparator compTest = new NumberComparator();
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {6, 6, "Числа равны"},
                {1, 20, "1 меньше 20"},
                {-1, 0, "-1 меньше 0"},
                {100, 5, "100 больше 5"},
        };
    }

    @Test(dataProvider = "data")
    public void testFactorial(int a, int b, String expected) {
        assertEquals(compTest.compare(a,b), expected);
    }
}