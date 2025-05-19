package lesson2_2;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        compareSumTo10(16,5); //Возвращает true\false
//        System.out.println(compareSumTo10(16,5)); //Проверка boolean вывода
        isPositive(-1);
        isPositiveBoolean(0);
//        System.out.println(isPositiveBoolean(-5)); //Проверка boolean вывода
        outputStringXtimes("gusli",3);
        isLeapYear(2000);
        System.out.println(isLeapYear(400));
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = -7;
        int b = 6;

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 101;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 100) {
            System.out.println("Зеленый");
        } else {
            System.out.println("Желтый");
        }
    }

    public static void compareNumbers() {
        int a = 6;
        int b = 6;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean compareSumTo10(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    public static void isPositive(int a) {
        if (a >= 0) {
            System.out.println(a + " положительное");
        } else {
            System.out.println(a + " отрицательное");
        }
    }

    public static boolean isPositiveBoolean(int a) {
        return a < 0;
    }

    public static void outputStringXtimes(String str, int x) {
        for (int i = 0; i < x; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }
}
