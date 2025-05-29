package lesson2_4.FiguresWork;

public interface FigureCount {
    double calculateArea();
    String showColor();
    double calculatePerimeter();

    default double calculateCirclePerimeter(double radius) {
        return 2 * Math.PI * radius;
    }

    default double calculateRectanglePerimeter(double length, double width) {
        return 2 * (length + width);
    }

    default double calculateTrianglePerimeter(double sideA, double sideB, double sideC) {
        return sideA + sideB + sideC;
    }
}
