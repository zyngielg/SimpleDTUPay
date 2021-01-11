package simplepay.exceptions;

import java.io.Serializable;

public class MerchantNotFoundException extends Exception implements Serializable {
    public MerchantNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
