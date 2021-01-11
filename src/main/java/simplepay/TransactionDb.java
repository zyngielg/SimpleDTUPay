package simplepay;

import simplepay.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDb {
    private List<Transaction> transactionList;

    public TransactionDb() {
        transactionList = new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
