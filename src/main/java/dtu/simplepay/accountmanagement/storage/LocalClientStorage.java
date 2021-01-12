package dtu.simplepay.accountmanagement.storage;

import dtu.simplepay.common.model.Customer;
import dtu.simplepay.common.model.Merchant;

import java.util.ArrayList;
import java.util.List;

public class LocalClientStorage {

    private static LocalClientStorage instance = null;

    public static LocalClientStorage getInstance() {
        if (instance == null) {
            instance = new LocalClientStorage();
        }
        return instance;
    }

    private final ArrayList<Customer> customerList;
    private final ArrayList<Merchant> merchantList;

    private LocalClientStorage() {
        customerList = new ArrayList<>();
        merchantList = new ArrayList<>();
    }

    public void registerCustomer(Customer client) {
        customerList.add(client);
    }

    public void registerMerchant(Merchant merchant) {
        merchantList.add(merchant);
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public List<Merchant> getAllMerchants() {
        return merchantList;
    }
}
