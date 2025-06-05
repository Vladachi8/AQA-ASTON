package lesson2_6.PhoneBook;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Сидорова Анастасия Алексеевна", "80212334455");
        phoneBook.add("Сидорова Анастасия Алексеевна", "80212111111");
        phoneBook.add("Андреев Артем Владимирович", "80212334455");
        phoneBook.add("Синица Маруся Анатольевна", "80212334455");
        phoneBook.add("Крано Владимир Игнатьевич", "80212334455");

        phoneBook.get("Сидорова Анастасия Алексеевна").forEach(System.out::println);
    }
}
