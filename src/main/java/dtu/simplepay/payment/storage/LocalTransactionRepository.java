package dtu.simplepay.payment.storage;

import dtu.simplepay.common.model.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class LocalTransactionRepository implements ITransactionRepository {

    private final LocalTransactionStorage storage = LocalTransactionStorage.getInstance();

    @Override
    public List<Transaction> getAllTransactions() {
        return storage.getTransactions();
    }

    @Override
    public void createTransaction(Transaction transaction) {
        storage.addNewTransaction(transaction);
    }

}
