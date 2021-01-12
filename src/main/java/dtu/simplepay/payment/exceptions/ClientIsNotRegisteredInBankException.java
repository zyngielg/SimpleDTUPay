package dtu.simplepay.payment.exceptions;

public class ClientIsNotRegisteredInBankException extends Exception {

    public ClientIsNotRegisteredInBankException() {
        super("client is not registered in FastMoney bank");
    }
}
