package lesson2_4.AnimalApp;

public class Bowl {
    private int quantity = 0;

    public Bowl(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 0;
            System.out.println("Количество еды не может быть отрицательным. Установлено 0.");
        }
    }

    public void addFood(int add) {
        if (add >= 0) {
            System.out.println("\nВ миске было: " + quantity);
            quantity += add;
            System.out.println("В миске после добавления: " + quantity);
        } else {
            System.out.println("Никаких отрицательных приростов!");
        }
    }

    public boolean decreaseFood(int amount) {
        if (amount <= quantity && amount >= 0) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    public int getQuantity() {
        return quantity;
    }
}
