package lesson2_3.Product;

import java.time.LocalDate;

public class Main {
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

        Product.printProductsArray(productsArray);
    }
}
