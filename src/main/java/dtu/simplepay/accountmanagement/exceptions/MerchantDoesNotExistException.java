package dtu.simplepay.accountmanagement.exceptions;

public class MerchantDoesNotExistException extends Exception {

    public MerchantDoesNotExistException(String name) {
        super("merchant with id " + name + " is unknown");
    }
}
