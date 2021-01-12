package dtu.simplepay.accountmanagement.resources;

import dtu.simplepay.accountmanagement.services.ClientRegistration;
import dtu.simplepay.common.model.Merchant;
import dtu.simplepay.payment.exceptions.ClientIsNotRegisteredInBankException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/merchants")
public class MerchantsResource {

    @Inject
    ClientRegistration clientRegistration;

    @GET
    public List<Merchant> getMerchants() {
        return clientRegistration.getAllRegisteredMerchants();
    }

    @POST
    public Response createMerchant(Merchant merchant) throws ClientIsNotRegisteredInBankException {
        var clientId = clientRegistration.registerClient(merchant);
        return Response.ok()
                .entity(clientId)
                .build();
    }
}
