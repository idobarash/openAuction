package rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * REST (JAX-RS) Exception handler.
 * It catches Exceptions and wrap it with an HTTP server error response.
 *
 * Author: Ido Barash
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new String(e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
