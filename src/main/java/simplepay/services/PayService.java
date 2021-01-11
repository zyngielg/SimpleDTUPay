package simplepay.services;

import simplepay.DtuPay;
import simplepay.TransactionDb;
import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;
import simplepay.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/pay")
public class PayService {
    DtuPay dtuPay;
    TransactionDb db;

    public PayService() {
        dtuPay = new DtuPay();
        db = new TransactionDb();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean pay(Transaction transaction) throws CustomerInsufficientFundsException, MerchantNotFoundException, CustomerNotFoundException {
        var result = dtuPay.pay(transaction);
        if (result) {
            db.addTransaction(transaction);
        }
        return result;
    }

    @GET
    public String hello() {
        return "hello";
    }
}
