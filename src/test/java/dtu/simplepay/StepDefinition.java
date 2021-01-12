package dtu.simplepay;

import dtu.simplepay.common.model.Client;
import dtu.simplepay.common.model.Customer;
import dtu.simplepay.common.model.Merchant;
import dtu.simplepay.payment.clients.FastMoneyServiceClient;
import dtu.simplepay.common.dtos.TransactionDto;
import dtu.ws.fastmoney.BankServiceException_Exception;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinition {

    private final SimpleDTUPay simpleDTUPay = new SimpleDTUPay();
    private final FastMoneyServiceClient fastMoneyServiceClient = new FastMoneyServiceClient();

    Customer customer;
    Merchant merchant;
    BigDecimal balance;

    String cid; String mid;

    List<String> accountIdList = new ArrayList<>();

    boolean successful;

    List<TransactionDto> allTransactions;

    String exceptionMessage;

    @After
    public void teardown() throws BankServiceException_Exception {
        for (var accountId : accountIdList) {
            fastMoneyServiceClient.deleteAccount(accountId);
        }
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(float amount) {
        try {
            successful = simpleDTUPay.pay(BigDecimal.valueOf(amount), cid, mid);
        } catch (Exception ex) {
            exceptionMessage = ex.getMessage();
        }
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(successful);
    }

    @When("the manager asks for a list of transactions")
    public void theManagerAsksForAListOfTransactions() {
        allTransactions = simpleDTUPay.getTransactionList();
    }

    @Then("the payment is not successful")
    public void thePaymentIsNotSuccessful() {
        assertFalse(successful);
    }

    @And("an error message is returned saying {string}")
    public void anErrorMessageIsReturnedSaying(String msg) {
        assertEquals(msg, exceptionMessage);
    }

    @Given("the customer {string} {string} with CPR {string} has a bank account")
    public void theCustomerWithCPRHasABankAccount(String name, String surname, String cpr) {
        customer = Customer.builder()
                .name(name)
                .surname(surname)
                .CPR(cpr)
                .build();
    }

    @And("the balance of that account is {float}")
    public void theBalanceOfThatAccountIs(float balance) {
        this.balance = BigDecimal.valueOf(balance);
    }

    @And("the customer is registered with DTUPay")
    public void theCustomerIsRegisteredWithDTUPay() throws BankServiceException_Exception {
        registerClientInBank(customer);
        cid = simpleDTUPay.registerCustomer(customer);
    }

    @And("the merchant {string} {string} with CPR number {string} has a bank account")
    public void theMerchantWithCPRNumberHasABankAccount(String name, String surname, String CPR) {
        merchant = Merchant.builder()
                .name(name)
                .surname(surname)
                .CPR(CPR)
                .build();
    }

    @And("the merchant is registered with DTUPay")
    public void theMerchantIsRegisteredWithDTUPay() throws BankServiceException_Exception {
        registerClientInBank(merchant);
        mid = simpleDTUPay.registerMerchant(merchant);
    }

    private void registerClientInBank(Client client) throws BankServiceException_Exception {
        var accountId = fastMoneyServiceClient.createBankAccountWithBalance(client, balance);
        accountIdList.add(accountId);
    }

    @And("the balance of the customer at the bank is {int} kr")
    public void theBalanceOfTheCustomerAtTheBankIsKr(int arg0) {
    }

    @And("the balance of the merchant at the bank is {int}")
    public void theBalanceOfTheMerchantAtTheBankIs(int arg0) {
    }

    @Then("the list contains a transaction where the customer paid {float} kr to the merchant")
    public void theListContainsATransactionWhereTheCustomerPaidKrToTheMerchant(float amount) {
        assertTrue(allTransactions.contains(new TransactionDto(cid, mid, BigDecimal.valueOf(amount))));
    }

    @Given("a customer with id {string}")
    public void aCustomerWithId(String arg0) {
        cid = arg0;
    }

    @And("a merchant with id {string}")
    public void aMerchantWithId(String arg0) {
        mid = arg0;
    }
}
