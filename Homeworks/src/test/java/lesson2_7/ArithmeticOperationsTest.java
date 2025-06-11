package lesson2_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {
    ArithmeticOperations operationsTest = new ArithmeticOperations();
    @DataProvider(name = "dataAdd")
    public Object[][] getDataAdd() {
        return new Object[][]{
                {10,5,15},
                {-8,6,-2},
                {50,50,100},
                {-1,1,0},
        };
    }

    @Test(dataProvider = "dataAdd")
    public void testAdd(int a, int b, int expected) {
        assertEquals(operationsTest.add(a,b), expected);
    }

    @DataProvider(name = "dataSubtract")
    public Object[][] getDataSubtract() {
        return new Object[][]{
                {10,5,5},
                {-8,6,-14},
                {50,50,0},
                {-1,1,-2},
        };
    }

    @Test(dataProvider = "dataSubtract")
    public void testSubtract(int a, int b, int expected) {
        assertEquals(operationsTest.subtract(a,b), expected);
    }

    @DataProvider(name = "dataMultiply")
    public Object[][] getDataMultiply() {
        return new Object[][]{
                {10,5,50},
                {-8,6,-48},
                {50,50,2500},
                {-1,1,-1},
        };
    }

    @Test(dataProvider = "dataMultiply")
    public void testMultiply(int a, int b, int expected) {
        assertEquals(operationsTest.multiply(a,b), expected);
    }

    @DataProvider(name = "dataDivide")
    public Object[][] getDataDivide() {
        return new Object[][]{
                {10,5,2},
                {-8,6,-1.3333333333333333},
                {50,50,1},
                {-1,1,-1},
        };
    }

    @Test(dataProvider = "dataDivide")
    public void testDivide(int a, int b, double expected) {
        assertEquals(operationsTest.divide(a,b), expected);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        operationsTest.divide(13, 0);
    }
}