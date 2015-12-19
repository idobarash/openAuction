package rest;

import model.User;

import javax.swing.text.StringContent;
import javax.ws.rs.*;

@Path("/user")
public interface UsersRestService {

    @GET
    @Path("/{id}")
    User get(@PathParam("id") Integer id);

    @POST
    User saveNewUser(User user);

    @PUT
    User updateUser(User user);

    @DELETE
    Boolean deleteUser(User user);
}
