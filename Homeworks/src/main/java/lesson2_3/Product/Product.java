package lesson2_3.Product;

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
        System.out.println();
        System.out.println("Название - " + this.name);
        System.out.println("Дата производства - " + this.productionDate);
        System.out.println("Производитель - " + this.fabricator);
        System.out.println("Страна происхождения - " + this.originCountry);
        System.out.println("Цена - " + this.price);
        System.out.println("Товар забронирован - " + this.isBooked);
    }

    // Метод для вывода массива товаров
    public static void printProductsArray(Product[] productsArray) {
        System.out.println();
        System.out.println("Задание 2 -> Массив товаров:");

        for (Product product : productsArray) {
            product.showInf();
        }
    }
}

