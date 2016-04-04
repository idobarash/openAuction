package rest;

import dto.ItemsWrapperDto;
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
    @Path("/category/{categoryName}")
    ItemsWrapperDto getItems(@PathParam("categoryName") String categoryName, @QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize);


    @POST
    @Path("/{userId}")
    Boolean postNewItem(@PathParam("userId") Integer userId, Item item);
}
