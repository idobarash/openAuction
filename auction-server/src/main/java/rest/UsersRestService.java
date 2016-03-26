package rest;

import entity.User;

import javax.ws.rs.*;

@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public interface UsersRestService {

    @GET
    @Path("/{id}")
    User get(@PathParam("id") Integer id);

    @POST
    Boolean saveNewUser(User user);

    @PUT
    User updateUser(User user);

    @DELETE
    Boolean deleteUser(User user);
}
