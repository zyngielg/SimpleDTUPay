package simplepay.model;

public class Transaction {
    public String customerId;
    public String merchantId;
    public int amount;

    public Transaction() {}

    public Transaction(String customerId, String merchantId, int amount) {
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.amount = amount;
    }
}