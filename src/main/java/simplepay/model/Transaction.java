package simplepay.model;

public class Transaction {
    public String customerId;
    public String merchantId;
    public double amount;

    public Transaction() {}

    public Transaction(String customerId, String merchantId, double amount) {
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.amount = amount;
    }
}