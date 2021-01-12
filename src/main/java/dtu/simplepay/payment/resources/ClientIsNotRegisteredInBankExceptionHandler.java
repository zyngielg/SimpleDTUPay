package dtu.simplepay.payment.resources;

import dtu.simplepay.payment.exceptions.ClientIsNotRegisteredInBankException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientIsNotRegisteredInBankExceptionHandler implements ExceptionMapper<ClientIsNotRegisteredInBankException> {

    @Override
    public Response toResponse(ClientIsNotRegisteredInBankException ex) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ex.getMessage())
                .build();
    }
}
