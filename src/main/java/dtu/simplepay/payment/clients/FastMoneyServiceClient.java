package dtu.simplepay.payment.clients;

import dtu.simplepay.common.model.Client;
import dtu.simplepay.common.model.Customer;
import dtu.simplepay.common.model.Merchant;
import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
public class FastMoneyServiceClient {

    BankServiceService service = new BankServiceService();

    public String createBankAccountWithBalance(Client client, BigDecimal balance) throws BankServiceException_Exception {

        var serviceProxy = service.getBankServicePort();
        return serviceProxy.createAccountWithBalance(
                DTUPayToFastMoneyMapper.mapUserToPerson(client),
                balance
        );
    }

    public boolean isPersonRegistered(Client client) {
        try {
            getAccount(client);
        } catch (BankServiceException_Exception e) {
            return false;
        }
        return true;
    }

    public void makeTransaction(Customer customer, Merchant merchant, BigDecimal amount) throws BankServiceException_Exception {
        var debtor = getAccount(customer);
        var creditor = getAccount(merchant);
        service
                .getBankServicePort()
                .transferMoneyFromTo(debtor.getId(), creditor.getId(), amount, "Made by SimpleDTUPay");
    }

    public BigDecimal getBalance(Client client) throws BankServiceException_Exception {
        var account = getAccount(client);
        return account.getBalance();
    }

    private Account getAccount(Client client) throws BankServiceException_Exception {
        return service
                .getBankServicePort()
                .getAccountByCprNumber(client.getCpr());
    }

    public void deleteAccount(String accountId) throws BankServiceException_Exception {
        service
                .getBankServicePort()
                .retireAccount(accountId);
    }
}
