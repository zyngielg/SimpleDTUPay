package simplepay;

import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;

import java.util.*;

public class SimpleDTUPay {
    private List<Merchant> merchants;
    private List<Customer> customers;

    public SimpleDTUPay() {
        customers = new ArrayList<Customer>() {{
            add(new Customer("cid1", 100));
        }};

        merchants = new ArrayList<Merchant>() {{
            add(new Merchant("mid1", 100));
        }};
    }

    public boolean pay(int amount, String cid, String mid) throws MerchantNotFoundException, CustomerNotFoundException,
            CustomerInsufficientFundsException {
        var merchant = getMerchant(mid);
        var customer = getCustomer(cid);

        if (customer.balance < amount) {
            return false;
        }

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
                () -> new MerchantNotFoundException(String.format("merchant with id %s is unknown\"\n", id)));
        customers.remove(customer);
    }

    public void removeMerchant(String id) throws MerchantNotFoundException {
        var merchant = customers.stream().filter(x -> x.id == id).findFirst().orElseThrow(
                () -> new MerchantNotFoundException(String.format("merchant with id %s is unknown\"\n", id)));
        if (merchant != null) {
            customers.remove(merchant);
        }
    }

    private Customer getCustomer(String id) throws CustomerNotFoundException {
        return customers.stream().filter(x -> x.id.equals(id)).findFirst().orElseThrow(
                () -> new CustomerNotFoundException(String.format("customer with id %s is unknown\n", id)));
    }

    private Merchant getMerchant(String id) throws MerchantNotFoundException {
        return merchants.stream().filter(x -> x.id.equals(id)).findFirst().orElseThrow(
                () -> new MerchantNotFoundException(String.format("merchant with id %s is unknown\n", id)));
    }
}
