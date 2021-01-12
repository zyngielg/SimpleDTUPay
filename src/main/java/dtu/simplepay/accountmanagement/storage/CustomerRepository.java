package dtu.simplepay.accountmanagement.storage;

import dtu.simplepay.common.model.Customer;
import dtu.simplepay.accountmanagement.exceptions.CustomerDoesNotExistException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerRepository implements IClientRepository<Customer> {

    private final LocalClientStorage storage = LocalClientStorage.getInstance();

    @Override
    public void registerClient(Customer customer) {
        storage.registerCustomer(customer);
    }

    @Override
    public List<Customer> getRegisteredClients() {
        return storage.getAllCustomers();
    }

    @Override
    public Customer getClient(String id) throws CustomerDoesNotExistException {
        var client = storage
                .getAllCustomers()
                .stream()
                .filter(c -> c.getId().equals(id))
                .collect(Collectors.toList());
        if (client.isEmpty()) {
            throw new CustomerDoesNotExistException(id);
        }
        return client.get(0);
    }
}
