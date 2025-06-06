package lesson2_4.FiguresWork;

public class Circle extends Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return calculateCirclePerimeter(radius);
    }

    @Override
    public String showColor() {
        return "заливка: " + fillColor + ", граница: " + borderColor;
    }
}
