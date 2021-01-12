package dtu.simplepay.accountmanagement.storage;

import dtu.simplepay.common.model.Client;
import dtu.simplepay.accountmanagement.exceptions.CustomerDoesNotExistException;
import dtu.simplepay.accountmanagement.exceptions.MerchantDoesNotExistException;

import java.util.List;

public interface IClientRepository<T extends Client> {
    void registerClient(T t);
    List<T> getRegisteredClients();
    T getClient(String id) throws CustomerDoesNotExistException, MerchantDoesNotExistException;
}
