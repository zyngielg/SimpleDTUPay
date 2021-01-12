package dtu.simplepay.accountmanagement.exceptions;

public class CustomerDoesNotExistException extends Exception {

    public CustomerDoesNotExistException(String name) {
        super("customer with id " + name + " is unknown");
    }
}
