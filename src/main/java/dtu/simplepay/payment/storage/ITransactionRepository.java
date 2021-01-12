package dtu.simplepay.payment.storage;

import dtu.simplepay.common.model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    List<Transaction> getAllTransactions();
    void createTransaction(Transaction transaction);
}
