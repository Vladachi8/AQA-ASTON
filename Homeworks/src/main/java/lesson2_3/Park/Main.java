package lesson2_3.Park;
import java.math.BigDecimal;
import java.time.LocalTime;

public class Main {
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
