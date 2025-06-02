package lesson2_4.AnimalApp;

public class Main {
    public static void main(String[] args) {
        Animal bobik = new Dog("Бобик");
        Animal tuzik = new Dog("Тузик");
        Animal barsik = new Dog("Барсик");
        Animal pushistik = new Cat("Пушистик");
        Animal volnistik = new Cat("Волнистик");
        Bowl bowl = new Bowl(10);

        Animal.getAnimalCount();
        Cat.getCatCount();
        Dog.getDogCount();

        barsik.run(-20);
        tuzik.run(37);
        bobik.run(501);
        pushistik.swim(100);


        volnistik.eat(bowl,10);
        bowl.addFood(21);

        Cat[] cats = {
                new Cat("Беляшик"),
                new Cat("Мурзик"),
                new Cat("Рыжик"),
                new Cat("Коксик"),
                new Cat("Вискис")
        };

        Cat.feedCats(cats,bowl);
    }
}
