package lesson2_4.FiguresWork;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5, "red", "blue"),
                new Rectangle(4, 6, "white", "yellow"),
                new Triangle(3, 4, 5, "black", "green")
        };

        for (Shape shape : shapes) {
            System.out.println("\n" + shape.getClass().getSimpleName() + ":");
            shape.printInfo();
        }
    }
}
