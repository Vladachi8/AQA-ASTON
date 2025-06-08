package lesson2_7;

public class TriangleArea {
    public static double calculateArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Основание и высота должны быть положительными числами");
        }

        return 0.5 * base * height;
    }
}
