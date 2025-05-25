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
    }

    abstract static class Animal {
        protected String name;
        private static int animalCount = 0;

        public Animal(String name) {
            this.name = name;
            animalCount++;
        }

        public abstract void run(int distance);
        public abstract void swim(int distance);

        public static void getAnimalCount() {
            System.out.println("Создано " + animalCount + " животное(ых)");
        }

        protected boolean isValidDistance(int distance) {
            return distance >= 0;
        }
    }

    public static class Cat extends Animal {
        private static final int MAX_RUN_DISTANCE = 200;
        private static int catCount = 0;

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
    }
}

