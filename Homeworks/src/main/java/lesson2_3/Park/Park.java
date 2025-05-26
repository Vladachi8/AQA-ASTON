package lesson2_3.Park;
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
}