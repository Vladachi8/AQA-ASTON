package lesson2_4.FiguresWork;

class Rectangle extends Shape {
    private double length;
    private double width;
    private String fillColor;
    private String borderColor;

    public Rectangle(double length, double width, String fillColor, String borderColor) {
        this.length = length;
        this.width = width;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return calculateRectanglePerimeter(length, width);
    }

    @Override
    public String showColor() {
        return "заливка: " + fillColor + ", граница: " + borderColor;
    }
}
