package simplepay;

public class Customer {
    public String id;
    public double balance;

    public Customer(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Customer(String id) {
        this.id = id;
        this.balance = 0;
    }
}
