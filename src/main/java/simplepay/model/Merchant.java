package simplepay.model;

public class Merchant {
    public String id;
    public int balance;

    public Merchant(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public Merchant(String id) {
        this.id = id;
        this.balance = 0;
    }
}
