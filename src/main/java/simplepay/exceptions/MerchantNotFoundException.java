package simplepay.exceptions;

public class MerchantNotFoundException extends Exception {
    public MerchantNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
