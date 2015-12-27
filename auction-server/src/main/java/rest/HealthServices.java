package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/health")
public interface HealthServices {

    @GET
    @Path("/")
    boolean checkHealth();
}
