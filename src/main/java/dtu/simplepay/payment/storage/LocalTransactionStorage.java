package dtu.simplepay.payment.storage;

import dtu.simplepay.common.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LocalTransactionStorage {

    private final ArrayList<Transaction> transactions;

    private LocalTransactionStorage() {
        transactions = new ArrayList<>();
    }

    private static LocalTransactionStorage instance = null;

    public static LocalTransactionStorage getInstance() {
        if (instance == null) {
            instance = new LocalTransactionStorage();
        }
        return instance;
    }

    public void addNewTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
