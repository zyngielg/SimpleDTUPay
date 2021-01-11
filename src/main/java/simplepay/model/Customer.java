package simplepay.model;

public class Customer {
    public String id;
    public int balance;

    public Customer(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public Customer(String id) {
        this.id = id;
        this.balance = 0;
    }
}
