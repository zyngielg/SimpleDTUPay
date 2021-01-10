package simplepay;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;
import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Cucumber
public class SimpleDTUPaySteps {
    String cid, mid;
    SimpleDTUPay dtuPay = new SimpleDTUPay();
    boolean successful;
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
            successful = dtuPay.pay(amount,cid,mid);
        } catch (MerchantNotFoundException | CustomerNotFoundException | CustomerInsufficientFundsException e) {
            e.printStackTrace();
        }
    }
    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(successful);;
    }
}
