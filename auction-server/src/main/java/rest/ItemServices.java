package rest;

import entity.ItemCategory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/items")
@Produces("application/json")
public interface ItemServices {

    @GET
    @Path("/categories")
    List<ItemCategory> getCategories();

    @GET
    @Path("/items/{userId}/category/{categoryName}")
    List<ItemCategory> getItems(@PathParam("userId") Integer userId, @PathParam("categoryName") String categoryName);
}
