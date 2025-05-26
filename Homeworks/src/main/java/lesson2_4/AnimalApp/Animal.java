package lesson2_4.AnimalApp;


abstract class Animal {
    protected String name;
    private static int animalCount = 0;
    protected static int bowl = 10;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);
    public abstract void eat(int eat);

    public static void getAnimalCount() {
        System.out.println("Создано " + animalCount + " животное(ых)");
    }

    protected boolean isValidDistance(int distance) {
        return distance >= 0;
    }

    public static void addFood(int add) {
        if (add >= 0) {
            System.out.println();
            System.out.println("В миске было: " + bowl);
            bowl += add;
            System.out.println("В миске после добавления: " + bowl);
        } else {
            System.out.println("Никаких отрицательных приростов!");
        }
    }
}


