package rest;

import entity.Item;
import entity.ItemCategory;

import javax.ws.rs.*;
import java.util.List;

@Path("/items")
@Produces("application/json")
public interface ItemsRestServices {

    @GET
    @Path("/categories")
    List<ItemCategory> getCategories();

    @GET
    @Path("/{userId}/category/{categoryName}")
    List<ItemCategory> getItems(@PathParam("userId") Integer userId, @PathParam("categoryName") String categoryName);


    @POST
    @Path("/{userId}")
    Boolean postNewItem(@PathParam("userId") Integer userId, Item item);
}
