package dtu.simplepay.accountmanagement.resources;

import dtu.simplepay.accountmanagement.services.ClientRegistration;
import dtu.simplepay.common.model.Customer;
import dtu.simplepay.payment.exceptions.ClientIsNotRegisteredInBankException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomersResource {

    @Inject
    ClientRegistration clientRegistration;

    @GET
    public List<Customer> getCustomers() {
        return clientRegistration.getAllRegisteredCustomers();
    }

    @POST
    public Response createCustomer(Customer customer) throws ClientIsNotRegisteredInBankException {
        var clientId = clientRegistration.registerClient(customer);
        return Response.ok()
                .entity(clientId)
                .build();
    }
}
