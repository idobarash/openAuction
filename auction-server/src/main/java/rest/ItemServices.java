package rest;

import entity.ItemCategory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/items")
@Produces("application/json")
public interface ItemServices {

    @GET
    @Path("/categories")
    List<ItemCategory> getCategories();
}
