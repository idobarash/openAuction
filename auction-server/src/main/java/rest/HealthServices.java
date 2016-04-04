package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * System health check REST endpoint
 * Used by automatic cloud services to check if the system is alive.
 *
 * Author: Ido Barash
 */
@Path("/health")
public interface HealthServices {

    @GET
    @Path("/")
    boolean checkHealth();
}
