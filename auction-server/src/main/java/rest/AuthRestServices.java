package rest;

import dto.LoginRequest;
import dto.LogoutRequest;
import dto.UserDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST services endpoint to handle Authentication
 *
 * Author: Ido Barash
 */
@Path("/auth")
@Produces("application/json")
@Consumes("application/json")
public interface AuthRestServices {

    /**
     * User login
     * @param loginRequest the data for the login
     * @return DTO of sucess or failure
     */
    @POST
    @Path("/login")
    UserDto login(LoginRequest loginRequest);

    /**
     * User logout
     * @param logoutRequest the user to logout
     */
    @POST
    @Path("/logout")
    void logout(LogoutRequest logoutRequest);
}
