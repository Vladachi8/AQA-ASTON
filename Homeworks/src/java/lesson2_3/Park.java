package lesson2_3;
import java.time.LocalTime;
import java.math.BigDecimal;

public class Park {
    public class Attraction {
        private String name;
        private LocalTime openingTime;
        private LocalTime closingTime;
        private BigDecimal price;

        public Attraction(String name,LocalTime openingTime,LocalTime closingTime,BigDecimal price) {
            this.name = name;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.price = price;
        }

        public void atrractionInf() {
            System.out.printf("Аттракцион: %s%nРаботает с %s до %s%nЦена: %.2f руб.%n%n",
                    name, openingTime, closingTime, price.setScale(2));
        }
    }


    public static void main(String[] args) {
        Park park = new Park();

        Park.Attraction rollerCoaster = park.new Attraction(
                "Американские горки",
                LocalTime.of(12, 0),
                LocalTime.of(22, 0),
                new BigDecimal("500.75"));

        Park.Attraction ferrisWheel = park.new Attraction(
                "Колесо обозрения",
                LocalTime.of(10, 0),
                LocalTime.of(20, 0),
                new BigDecimal("250.50"));

        rollerCoaster.atrractionInf();
        ferrisWheel.atrractionInf();
    }
}