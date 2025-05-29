package lesson2_4.FiguresWork;

abstract class Shape implements FigureCount {
    public abstract double calculateArea();
    public abstract String showColor();

    public void printInfo() {
        System.out.println("Площадь: " + String.format("%.2f", calculateArea()));
        System.out.println("Периметр: " + String.format("%.2f", calculatePerimeter()));
        System.out.println(showColor());
    }
}
