package rest;

import entity.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/auth")
public interface AuthRestServices {

    @POST
    @Path("/login")
    User login(String username, String password);

    @POST
    @Path("/logout")
    User logout(String username, String password);
}
