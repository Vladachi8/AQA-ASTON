package lesson2_4.AnimalApp;

public class Dog extends Animal {
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (!isValidDistance(distance)) {
            System.out.println(name + ": Дистанция не может быть отрицательной!");
            return;
        }

        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробежал " + distance + "м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + "м. (максимум " + MAX_RUN_DISTANCE + "м.)");
        }
    }

    @Override
    public void swim(int distance) {
        if (!isValidDistance(distance)) {
            System.out.println(name + ": Дистанция не может быть отрицательной!");
            return;
        }

        if (distance <= MAX_SWIM_DISTANCE) {
            System.out.println(name + " проплыл: " + distance + "м");
        } else {
            System.out.println(name + " очень старался и проплыл свой максимум: : " + MAX_SWIM_DISTANCE + "м");
        }
    }

    public static void getDogCount() {
        System.out.println("Создано " + dogCount + " собака(и)");
    }

    @Override
    public void eat(int eat) {};
}
