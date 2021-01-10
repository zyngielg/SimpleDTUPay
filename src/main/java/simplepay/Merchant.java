package simplepay;

public class Merchant {
    public String id;
    public double balance;

    public Merchant(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Merchant(String id) {
        this.id = id;
        this.balance = 0;
    }
}
