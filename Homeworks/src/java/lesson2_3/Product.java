package lesson2_3;

import java.time.LocalDate;

public class Product {
    protected String name;
    protected LocalDate productionDate;
    protected String fabricator;
    protected String originCountry;
    protected int price;
    protected Boolean isBooked;

    public Product (String name, LocalDate productionDate, String fabricator, String originCountry, int price, Boolean booking) {
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
                LocalDate.of(2022,5,15),
                "Firma bla bla",
                "USA",
                288,
                true
        );

        product1.showInf();
    }
}
