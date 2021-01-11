package simplepay;

import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;
import simplepay.model.Customer;
import simplepay.model.Merchant;
import simplepay.model.Transaction;

import java.util.*;

public class DtuPay {
    private Set<Merchant> merchants;
    private Set<Customer> customers;

    public DtuPay() {
        customers = new HashSet<>() {{
            add(new Customer("cid1", 100));
        }};

        merchants = new HashSet<>() {{
            add(new Merchant("mid1", 100));
        }};
    }

    public boolean pay(Transaction transaction) throws MerchantNotFoundException, CustomerNotFoundException,
            CustomerInsufficientFundsException {
        var amount = transaction.amount;
        var customer = getCustomer(transaction.customerId);
        if (customer.balance < amount) {
            return false;
        }
        var merchant = getMerchant(transaction.merchantId);

        customer.balance -= amount;
        merchant.balance += amount;

        return true;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public void removeCustomer(String id) throws MerchantNotFoundException {
        var customer = customers.stream().filter(x -> x.id == id).findFirst().orElseThrow(
                () -> new MerchantNotFoundException(String.format("merchant with id %s is unknown", id)));
        customers.remove(customer);
    }

    public void removeMerchant(String id) throws MerchantNotFoundException {
        var merchant = customers.stream().filter(x -> x.id == id).findFirst().orElseThrow(
                () -> new MerchantNotFoundException(String.format("merchant with id %s is unknown", id)));
        if (merchant != null) {
            customers.remove(merchant);
        }
    }

    private Customer getCustomer(String id) throws CustomerNotFoundException {
        return customers.stream().filter(x -> x.id.equals(id)).findFirst().orElseThrow(
                () -> new CustomerNotFoundException(String.format("customer with id %s is unknown", id)));
    }

    private Merchant getMerchant(String id) throws MerchantNotFoundException {
        return merchants.stream().filter(x -> x.id.equals(id)).findFirst().orElseThrow(
                () -> new MerchantNotFoundException(String.format("merchant with id %s is unknown", id)));
    }
}
