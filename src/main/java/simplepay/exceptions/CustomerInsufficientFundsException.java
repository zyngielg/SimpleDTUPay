package simplepay.exceptions;

public class CustomerInsufficientFundsException extends Exception {
    public CustomerInsufficientFundsException(String errorMessage) {
        super(errorMessage);
    }
}
