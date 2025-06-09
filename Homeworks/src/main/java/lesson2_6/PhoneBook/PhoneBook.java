package lesson2_6.PhoneBook;

import java.util.*;

public class PhoneBook {
    private final Map<String, List<String>> phoneBook = new TreeMap<>();

    public void add(String fio, String number) {
        phoneBook.putIfAbsent(fio, new ArrayList<>());
        phoneBook.get(fio).add(number);
    }

    public List<String> get(String fio) {
        return phoneBook.getOrDefault(fio, new ArrayList<>());
    }
}
