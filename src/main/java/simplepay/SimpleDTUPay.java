package simplepay;

import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class SimpleDTUPay {
    private Set<Merchant> merchants;
    private Set<Customer> customers;

    public SimpleDTUPay() {
        customers = new HashSet<>() {{
            add(new Customer("cid1"));
        }};

        merchants = new HashSet<>() {{
            add(new Merchant("mid1"));
        }};
    }

    public boolean pay(int amount, String cid, String mid) throws MerchantNotFoundException, CustomerNotFoundException,
            CustomerInsufficientFundsException {
        var merchant = getMerchant(mid);
        String errorMessage;
        if (merchant == null) {
            errorMessage = String.format("Merchant with id=%s was not found", mid);
            throw new MerchantNotFoundException(errorMessage);
        }
        var customer = getCustomer(cid);
        if (customer == null) {
            errorMessage = String.format("Customer with id=%s was not found", cid);
            throw new CustomerNotFoundException(errorMessage);
        }

        if (customer.balance < amount) {
            return false;
//            errorMessage = String.format("Customer with id=%s has insufficient funds to complete the transaction" +
//                    " from merchant with id=%s", cid, mid);
//            throw new CustomerInsufficientFundsException(errorMessage);
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

    public void removeCustomer(String id) {
        var customer = customers.stream().filter(x -> x.id == id).findFirst().orElse(null);
        if (customer != null) {
            customers.remove(customer);
        }
    }

    public void removeMerchant(String id) {
        var merchant = customers.stream().filter(x -> x.id == id).findFirst().orElse(null);
        if (merchant != null) {
            customers.remove(merchant);
        }
    }

    private Customer getCustomer(String id) {
        return customers.stream().filter(x -> x.id == id).findFirst().orElse(null);
    }

    private Merchant getMerchant(String id) {
        return merchants.stream().filter(x -> x.id == id).findFirst().orElse(null);
    }
}
