package lesson2_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {
    TriangleArea areaTest = new TriangleArea();
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {10,20,100},
                {1,5,2.5},
                {35,82,1435},
        };
    }

    @Test(dataProvider = "data")
    public void testArea(double a, double b, double expectedArea) {
        assertEquals(areaTest.calculateArea(a,b), expectedArea);
    }

    @DataProvider(name = "dataNegative")
    public Object[][] getDataNegative() {
        return new Object[][]{
                {-2, 6},
                {-0.1, 20},
                {-1, 0},
                {0, 5},
        };
    }

    @Test(dataProvider = "dataNegative",expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaInvalidInput(double a, double b) {
        areaTest.calculateArea(a,b);
    }
}