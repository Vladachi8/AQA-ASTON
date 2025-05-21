package lesson2_2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        compareSumTo10(1, 5);
        isPositive(5);
        isPositiveBoolean(-2);
        outputStringXtimes("gusli", 3);
        isLeapYear(20);
        invertElemMass();
        fillArray();
        multiplyLessThanSix();
        fillDiagonalMatrix();
        createArr(5, 3);
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
        boolean result = (a + b >= 10 && a + b <= 20);
        System.out.println("Сумма чисел в диапазоне 10-20 " + result);
        return result;
    }

    public static void isPositive(int a) {
        if (a >= 0) {
            System.out.println(a + " положительное");
        } else {
            System.out.println(a + " отрицательное");
        }
    }

    public static boolean isPositiveBoolean(int a) {
        boolean result = (a < 0);
        System.out.println(a + " Отрицательное? - " + result);
        return result;
    }

    public static void outputStringXtimes(String str, int x) {
        for (int i = 0; i < x; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        boolean result = (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
        System.out.println(year + " високосный? - " + result);
        return result;
    }

    public static void invertElemMass() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println(Arrays.toString(arr) + " массив до");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr) + " после");
    }

    public static void fillArray() {
        int[] arr = new int[100];
        int i = 0;

        while (i < arr.length) {
            arr[i] = i + 1;
            i++;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void multiplyLessThanSix() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println(Arrays.toString(arr) + " массив до");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] < 6 ? arr[i] * 2 : arr[i];
        }
        System.out.println(Arrays.toString(arr) + " после");
    }

    public static void fillDiagonalMatrix() {
        int[][] matrix = new int[5][5];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = 1;
                } else if (matrix.length - 1 - i == j) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] createArr(int len, int initialValue) {
        int[] arr = new int[len];
        int i = 0;
        while (i < arr.length) {
            arr[i] = initialValue;
            i++;
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

}
