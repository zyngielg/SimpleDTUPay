package simplepay.services;

import simplepay.DtuPay;
import simplepay.TransactionDb;
import simplepay.exceptions.CustomerInsufficientFundsException;
import simplepay.exceptions.CustomerNotFoundException;
import simplepay.exceptions.MerchantNotFoundException;
import simplepay.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/simple-dtu-pay")
public class DTUSimplePayService {
    DtuPay dtuPay;
    TransactionDb db;

    public DTUSimplePayService() {
        dtuPay = new DtuPay();
        db = new TransactionDb();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pay")
    public Response pay(Transaction transaction) throws CustomerInsufficientFundsException, MerchantNotFoundException, CustomerNotFoundException {
        var result = dtuPay.pay(transaction);
        if (result) {
            db.addTransaction(transaction);
        }
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/transaction-list")
    public Response getTransactionList() {
        return Response.ok().entity(db.getTransactionList()).build();
    }
}
