package simplepay;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;
import simplepay.model.Transaction;

import static org.junit.jupiter.api.Assertions.*;

public class PayServiceSteps {
    String cid, mid;
    String exceptionMessage;
    boolean successful;

    DtuPay dtuPay;


    @Given("a customer with id {string}")
    public void aCustomerWithId(String cid) {
        this.cid = cid;
    }

    @Given("a merchant with id {string}")
    public void aMerchantWithId(String mid) {
        this.mid = mid;
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int amount) {
        try {
            dtuPay = new DtuPay();
            var transaction = new Transaction(cid, mid, amount);
            successful = dtuPay.pay(transaction);
        } catch (MerchantNotFoundException | CustomerNotFoundException | CustomerInsufficientFundsException e) {
            exceptionMessage = e.getMessage();
        }
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(successful);;
    }

    @Then("the payment is not successful")
    public void thePaymentIsNotSuccessful() {
        assertFalse(successful);
    }

    @And("an error message is returned saying {string}")
    public void anErrorMessageIsReturnedSaying(String message) {
        assertEquals(exceptionMessage, message);
    }

}
