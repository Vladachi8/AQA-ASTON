package lesson2_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {
    Factorial newFactorialTest = new Factorial();
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
        assertEquals(newFactorialTest.factorial(a), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeInput() {
        newFactorialTest.factorial(-1);
    }
}