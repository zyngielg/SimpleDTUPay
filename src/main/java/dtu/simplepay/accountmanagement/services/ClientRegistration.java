package dtu.simplepay.accountmanagement.services;

import dtu.simplepay.common.model.Client;
import dtu.simplepay.common.model.Customer;
import dtu.simplepay.common.model.Merchant;
import dtu.simplepay.payment.clients.FastMoneyServiceClient;
import dtu.simplepay.payment.exceptions.ClientIsNotRegisteredInBankException;
import dtu.simplepay.accountmanagement.storage.CustomerRepository;
import dtu.simplepay.accountmanagement.storage.MerchantRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ClientRegistration {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    MerchantRepository merchantRepository;

    @Inject
    FastMoneyServiceClient fastMoneyServiceClient;

    public List<Customer> getAllRegisteredCustomers() {
        return customerRepository.getRegisteredClients();
    }

    public List<Merchant> getAllRegisteredMerchants() {
        return merchantRepository.getRegisteredClients();
    }

    public String registerClient(Customer customer) throws ClientIsNotRegisteredInBankException {
        if (!checkIfClientRegisteredInFastMoney(customer)) {
            throw new ClientIsNotRegisteredInBankException();
        }
        if (customer.getId() == null) {
            customer.setId(UUID.randomUUID().toString());
        }
        customerRepository.registerClient(customer);
        return customer.getId();
    }

    public String registerClient(Merchant merchant) throws ClientIsNotRegisteredInBankException {
        if (!checkIfClientRegisteredInFastMoney(merchant)) {
            throw new ClientIsNotRegisteredInBankException();
        }
        if (merchant.getId() == null) {
            merchant.setId(UUID.randomUUID().toString());
        }
        merchantRepository.registerClient(merchant);
        return merchant.getId();
    }

    private boolean checkIfClientRegisteredInFastMoney(Client client) {
        return fastMoneyServiceClient.isPersonRegistered(client);
    }
}
