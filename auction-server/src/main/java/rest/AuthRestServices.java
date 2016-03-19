package rest;

import dto.LoginRequest;
import dto.LogoutRequest;
import entity.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/auth")
@Produces("application/json")
@Consumes("application/json")
public interface AuthRestServices {

    @POST
    @Path("/login")
    User login(LoginRequest loginRequest);

    @POST
    @Path("/logout")
    User logout(LogoutRequest logoutRequest);
}
