package lesson2_7;

public class Main {
    public static void main(String[] args) {
        try {
            Factorial.factorial(-1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("Факториал 10: " + Factorial.factorial(10));
        System.out.println("Площадь треугольника: " + TriangleArea.calculateArea(6, 3));
        System.out.println("4 + 6 = " + ArithmeticOperations.add(4, 6));
        System.out.println("330 - 30 = " + ArithmeticOperations.subtract(330, 30));
        System.out.println("2 * 100 = " + ArithmeticOperations.multiply(2, 100));
        System.out.println("21 / 3 = " + ArithmeticOperations.divide(21, 3));
        System.out.println(NumberComparator.compare(500, 2));
    }
}
