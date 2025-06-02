package lesson2_4.AnimalApp;

public class Cat extends Animal {
    private static final int MAX_RUN_DISTANCE = 200;
    private static int catCount = 0;
    private boolean full = false;

    public Cat(String name) {
        super(name);
        catCount++;
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
            System.out.println(name + " не может пробежать " + distance + " м. (максимум " + MAX_RUN_DISTANCE + "м.)");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(this.name + " не плавает и поэтому расстояние в " + distance + "м не для него");
    }

    public static void getCatCount() {
        System.out.println("Создано " + catCount + " кот(ов)");
    }

    @Override
    public void eat(Bowl bowl, int amount) {
        System.out.println("\nВ миске " + bowl.getQuantity() + " еды");
        if (bowl.getQuantity() >= amount) {
            bowl.decreaseFood(amount);
            full = true;
            System.out.println(name + " съел " + amount + ", в миске осталось: " + bowl.getQuantity() + ", сытость " + full);
        } else {
            System.out.println(name + "у eды не хватило, сытость " + full);
        }
    }

    public static void feedCats(Cat[] cats, Bowl bowl) {
        final int DEFAULT_FOOD_AMOUNT = 5;
        for (Cat cat : cats) {
            cat.eat(bowl, DEFAULT_FOOD_AMOUNT);
        }
    }
}
