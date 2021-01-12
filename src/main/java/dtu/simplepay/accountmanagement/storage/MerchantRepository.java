package dtu.simplepay.accountmanagement.storage;

import dtu.simplepay.common.model.Merchant;
import dtu.simplepay.accountmanagement.exceptions.MerchantDoesNotExistException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MerchantRepository implements IClientRepository<Merchant> {

    private final LocalClientStorage storage = LocalClientStorage.getInstance();

    @Override
    public void registerClient(Merchant merchant) {
        storage.registerMerchant(merchant);
    }

    @Override
    public List<Merchant> getRegisteredClients() {
        return storage.getAllMerchants();
    }

    @Override
    public Merchant getClient(String id) throws MerchantDoesNotExistException {
        var clients = storage
                .getAllMerchants()
                .stream()
                .filter(c -> c.getId().equals(id))
                .collect(Collectors.toList());
        if (clients.isEmpty()) {
            throw new MerchantDoesNotExistException(id);
        }
        return clients.get(0);
    }
}
