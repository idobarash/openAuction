package rest;

import entity.User;

import javax.ws.rs.*;

/**
 * REST services endpoint to handle user operations
 *
 * Author: Ido Barash
 */
@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public interface UsersRestService {

    /**
     * Find user by id.
     *
     * @param id the id of the user to find
     * @return user if found
     */
    @GET
    @Path("/{id}")
    User get(@PathParam("id") Integer id);

    /**
     * Store a new user
     *
     * @param user the user to store.
     * @return true if success
     */
    @POST
    Boolean saveNewUser(User user);

    /**
     * Update user data
     *
     * @param user the data to update
     * @return the updated user
     */
    @PUT
    User updateUser(User user);

    /**
     * Delete a user
     * NOTICE: this is a soft delete - it only disables the user.
     *
     * @param user the user to disable.
     * @return true if success
     */
    @DELETE
    Boolean deleteUser(User user);
}
