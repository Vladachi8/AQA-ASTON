package lesson2_3;

import java.time.LocalDate;

public class Product {
    protected String name;
    protected LocalDate productionDate;
    protected String fabricator;
    protected String originCountry;
    protected int price;
    protected Boolean isBooked;

    public Product(String name, LocalDate productionDate, String fabricator, String originCountry, int price, Boolean booking) {
        this.name = name;
        this.productionDate = productionDate;
        this.fabricator = fabricator;
        this.originCountry = originCountry;
        this.price = price;
        this.isBooked = booking;
    }

    protected void showInf() {
        System.out.println("Название - " + this.name);
        System.out.println("Дата производства - " + this.productionDate);
        System.out.println("Производитель - " + this.fabricator);
        System.out.println("Страна происхождения - " + this.originCountry);
        System.out.println("Цена - " + this.price);
        System.out.println("Товар забронирован - " + this.isBooked);
    }

    public static void main(String[] args) {
        Product product1 = new Product(
                "Телевизор",
                LocalDate.of(2022, 5, 15),
                "Firma bla bla",
                "USA",
                288,
                true
        );

        product1.showInf();

        //2 задание
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product(
                "Samsung S25 Ultra",
                LocalDate.of(2025, 5, 31),
                "Samsung Corp.",
                "Korea",
                5599,
                true
        );

        productsArray[1] = new Product(
                "Samsung S24 Ultra",
                LocalDate.of(2020, 1, 1),
                "Samsung Corp.",
                "Korea",
                4599,
                false
        );

        productsArray[2] = new Product(
                "Samsung 65",
                LocalDate.of(2023, 6, 7),
                "Samsung Corp.",
                "Korea",
                7599,
                false
        );

        productsArray[3] = new Product(
                "LG 80",
                LocalDate.of(2025, 12, 31),
                "LG Electronics",
                "USA",
                18000,
                true
        );

        productsArray[4] = new Product(
                "LG 55",
                LocalDate.of(2022, 7, 15),
                "LG Electronics",
                "USA",
                9000,
                true
        );

        printProductsArray(productsArray);
    }

    // Метод для вывода массива товаров
    public static void printProductsArray(Product[] productsArray) {
        System.out.println();
        System.out.println("Задание 2 -> Массив товаров:");

        for (int i = 0; i < productsArray.length; i++) {
            System.out.println();
            productsArray[i].showInf();
        }
    }
}

