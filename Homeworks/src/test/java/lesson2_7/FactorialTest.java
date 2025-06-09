package lesson2_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {0, 1},
                {1, 1},
                {4, 24},
                {10, 3628800},
        };
    }

    @Test(dataProvider = "data")
    public void testFactorial(int a, int expected) {
        assertEquals(Factorial.factorial(a), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeInput() {
        Factorial.factorial(-1);
    }
}