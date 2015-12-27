package rest;

import entity.User;

import javax.ws.rs.*;

@Path("/users")
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
