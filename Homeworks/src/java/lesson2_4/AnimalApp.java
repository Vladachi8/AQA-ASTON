package lesson2_4;

public class AnimalApp {
    public static void main(String[] args) {
        Animal bobik = new Dog("Бобик");
        Animal tuzik = new Dog("Тузик");
        Animal barsik = new Dog("Барсик");
        Animal pushistik = new Cat("Пушистик");
        Animal volnistik = new Cat("Волнистик");

        Animal.getAnimalCount();
        Cat.getCatCount();
        Dog.getDogCount();

        barsik.run(-20);
        tuzik.run(37);
        bobik.run(501);
        pushistik.swim(100);

        volnistik.eat(2);
        Animal.addFood(20);

        Cat[] cats = {
                new Cat("Беляшик", 2),
                new Cat("Мурзик", 28),
                new Cat("Рыжик", 5),
                new Cat("Коксик", 21),
                new Cat("Вискис", 1)
        };

        Cat.feedCats(cats);
    }

    abstract static class Animal {
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
            if(add >= 0) {
                System.out.println();
                System.out.println("В миске было: " + bowl);
                bowl+=add;
                System.out.println("В миске после добавления: " + bowl);
            } else {
                System.out.println("Никаких отрицательных приростов!");
            }
        }
    }

    public static class Cat extends Animal {
        private static final int MAX_RUN_DISTANCE = 200;
        private static int catCount = 0;
        private boolean full = false;
        private int appetite;


        public Cat(String name) {
            super(name);
            catCount++;
        }

        public Cat(String name, int appetite) {
            super(name);
            this.appetite = appetite;
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
        public void eat(int eat) {
            System.out.println();
            System.out.println("В миске " + bowl + " еды");
            if (bowl >= eat) {
                bowl -= eat;
                full = true;
                System.out.println(name + " съел " + eat + ", в миске осталось: " + bowl + ", сытость " + full);
            } else {
                System.out.println(name + "у eды не хватило, сытость " + full);
            }
        }

        public static void feedCats(Cat[] cats) {
            for (Cat cat : cats) {
                cat.eat(cat.appetite);
            }
        }
    }

    public static class Dog extends Animal {
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
}

