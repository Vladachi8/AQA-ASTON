package lesson2_4.AnimalApp;


abstract class Animal {
    protected String name;
    private static int animalCount = 0;


    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);
    public abstract void eat(Bowl bowl);

    public static void getAnimalCount() {
        System.out.println("Создано " + animalCount + " животное(ых)");
    }

    protected boolean isValidDistance(int distance) {
        return distance >= 0;
    }
}


