package lesson2_7;

public class Main {
    public static void main(String[] args) {
        Factorial newFactorial = new Factorial();
        ArithmeticOperations operations = new ArithmeticOperations();
        NumberComparator comp = new NumberComparator();
        TriangleArea area = new TriangleArea();

        try {
            newFactorial.factorial(-1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("Факториал 10: " + newFactorial.factorial(10));
        System.out.println("Площадь треугольника: " + area.calculateArea(6, 3));
        System.out.println("4 + 6 = " + operations.add(4, 6));
        System.out.println("330 - 30 = " + operations.subtract(330, 30));
        System.out.println("2 * 100 = " + operations.multiply(2, 100));
        System.out.println("21 / 3 = " + operations.divide(21, 3));
        System.out.println(comp.compare(500, 2));
    }
}
