package dtu.simplepay.accountmanagement.resources;

import dtu.simplepay.accountmanagement.exceptions.CustomerDoesNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerNotFoundExceptionHandler implements ExceptionMapper<CustomerDoesNotExistException> {
    @Override
    public Response toResponse(CustomerDoesNotExistException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
