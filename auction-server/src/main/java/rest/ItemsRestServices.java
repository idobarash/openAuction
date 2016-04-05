package rest;

import dto.ItemsWrapperListDto;
import entity.Item;
import entity.ItemCategory;

import javax.ws.rs.*;
import java.util.List;

/**
 * REST services endpoint to handle auction items operations
 *
 * Author: Ido Barash
 */
@Path("/items")
@Produces("application/json")
public interface ItemsRestServices {

    /**
     * Endpoint exposing all categories
     * @return list of all the categories
     */
    @GET
    @Path("/categories")
    List<ItemCategory> getCategories();

    /**
     * Get items list per category
     * @param categoryId the category
     * @param pageNumber current page
     * @param pageSize total items in page
     * @return DTO object containing a list of the items in the page and
     *             counter for the total items in the category
     */
    @GET
    @Path("/category/{categoryId}")
    ItemsWrapperListDto getItems(@PathParam("categoryId") Integer categoryId, @QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize);

    /**
     * Get items list data
     * @param pageNumber current page
     * @param pageSize total items in page
     * @return DTO object containing a list of the items in the page and
     *             counter for the total items in the category
     */
    @GET
    ItemsWrapperListDto getItems(@QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize);


    /**
     * Insert a new item to the DB.
     * @param userId the usre inserting the data.
     * @param item the item data to store.
     * @return true if success.
     */
    @POST
    @Path("/{userId}")
    Boolean postNewItem(@PathParam("userId") Integer userId, Item item);

    /**
     * Load a specific item.
     * @param itemId the item id to load by
     * @return the item if exists
     */
    @GET
    @Path("/{itemId}")
    Item getItem(@PathParam("itemId") Integer itemId);

    /**
     * Place a new bid on item
     * @param userId the usre inserting the data.
     * @param itemId the item id to place bid on.
     * @param bidSum the sum of the bid.
     * @return true if success.
     */
    @POST
    @Path("/{itemId}/user/{userId}/bid/{bidSum}")
    Boolean postNewItem(@PathParam("itemId") Integer itemId, @PathParam("userId") Integer userId, @PathParam("bidSum") Integer bidSum);
}
