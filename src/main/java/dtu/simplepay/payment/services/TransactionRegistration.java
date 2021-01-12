package dtu.simplepay.payment.services;

import dtu.simplepay.common.model.Transaction;
import dtu.simplepay.accountmanagement.exceptions.CustomerDoesNotExistException;
import dtu.simplepay.accountmanagement.exceptions.MerchantDoesNotExistException;
import dtu.simplepay.common.dtos.TransactionDto;
import dtu.simplepay.accountmanagement.storage.CustomerRepository;
import dtu.simplepay.payment.storage.ITransactionRepository;
import dtu.simplepay.accountmanagement.storage.MerchantRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransactionRegistration {

    @Inject
    ITransactionRepository transactionRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    MerchantRepository merchantRepository;

    public List<TransactionDto> retrieveAllTransactions() {
        var transactions = transactionRepository.getAllTransactions();
        return transactions.stream()
                .map(t -> new TransactionDto(
                    t.getCustomer().getId(),
                    t.getMerchant().getId(),
                    t.getAmount()))
                .collect(Collectors.toList());
    }

    public void registerNewTransaction(TransactionDto transaction) throws CustomerDoesNotExistException, MerchantDoesNotExistException {
        var customer = customerRepository.getClient(transaction.getCustomerId());
        var merchant = merchantRepository.getClient(transaction.getMerchantId());
        transactionRepository.createTransaction(new Transaction(customer, merchant, transaction.getAmount()));
    }
}
