package lesson2_4.AnimalApp;

public class Main {
    public static void main(String[] args) {
        Animal bobik = new Dog("Бобик");
        Animal tuzik = new Dog("Тузик");
        Animal barsik = new Dog("Барсик");
        Animal pushistik = new Cat("Пушистик");
        Animal volnistik = new Cat("Волнистик", 6);
        Bowl bowl = new Bowl(10);

        Animal.getAnimalCount();
        Cat.getCatCount();
        Dog.getDogCount();

        barsik.run(-20);
        tuzik.run(37);
        bobik.run(501);
        pushistik.swim(100);


        volnistik.eat(bowl);
        bowl.addFood(30);

        Cat[] cats = {
                new Cat("Беляшик", 2),
                new Cat("Мурзик", 28),
                new Cat("Рыжик", 5),
                new Cat("Коксик", 4),
                new Cat("Вискис", 1)
        };

        Cat.feedCats(cats,bowl);
    }
}
