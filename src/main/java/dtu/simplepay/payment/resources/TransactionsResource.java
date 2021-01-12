package dtu.simplepay.payment.resources;

import dtu.simplepay.payment.services.TransactionRegistration;
import dtu.simplepay.common.dtos.TransactionDto;
import dtu.simplepay.accountmanagement.exceptions.CustomerDoesNotExistException;
import dtu.simplepay.accountmanagement.exceptions.MerchantDoesNotExistException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/transactions")
public class TransactionsResource {

    @Inject
    TransactionRegistration transactionRegistration;

    @GET
    public List<TransactionDto> getTransactions() {
        return transactionRegistration.retrieveAllTransactions();
    }

    @POST
    public Response createTransaction(TransactionDto transaction) throws MerchantDoesNotExistException, CustomerDoesNotExistException {
        transactionRegistration.registerNewTransaction(transaction);
        return Response.ok().build();
    }

}
