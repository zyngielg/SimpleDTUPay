package simplepay.exceptionHandlers;

import simplepay.exceptions.CustomerInsufficientFundsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerInsufficientFundsExceptionHandler implements ExceptionMapper<CustomerInsufficientFundsException> {
    @Override
    public Response toResponse(CustomerInsufficientFundsException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}