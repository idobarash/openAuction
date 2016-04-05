package rest;

import dto.ItemsWrapperListDto;
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

    /**
     * Retrieve a list of the ongoing auctions of a specific
     * user.
     *
     * @param userId the user ID
     * @param pageNumber the page number to load
     * @param pageSize the total page size
     * @return A wrapper containing list of all the ongoing auctions and counter.
     */
    @GET
    @Path("{userId}/ongoing")
    ItemsWrapperListDto getUserOngoingAuctions(@PathParam("userId") Integer userId, @QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize);

    /**
     * Retrieve a list of the ongoing auctions of a specific user of which the
     * user has participated.
     *
     * @param userId the user ID
     * @param pageNumber the page number to load
     * @param pageSize the total page size
     * @return A wrapper containing list of all the auctions the user had participated
     *                  and are ongoing, and a counter.
     */
    @GET
    @Path("{userId}/bids")
    ItemsWrapperListDto getUserOngoingBids(@PathParam("userId") Integer userId,  @QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize);

    /**
     * Retrieve a list of the finished user auctions.
     * user.
     *
     * @param userId the user ID
     * @param pageNumber the page number to load
     * @param pageSize the total page size
     * @return A wrapper containing list of all the finished auctions and counter.
     */
    @GET
    @Path("{userId}/finished")
    ItemsWrapperListDto getUserFinishedAuctions(@PathParam("userId") Integer userId,  @QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize);
}
