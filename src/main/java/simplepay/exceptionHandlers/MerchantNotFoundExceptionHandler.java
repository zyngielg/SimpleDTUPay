package simplepay.exceptionHandlers;

import simplepay.exceptions.MerchantNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MerchantNotFoundExceptionHandler implements ExceptionMapper<MerchantNotFoundException> {
    @Override
    public Response toResponse(MerchantNotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}