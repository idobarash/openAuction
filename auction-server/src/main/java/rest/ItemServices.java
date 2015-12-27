package rest;

import entity.ItemCategory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/items")
public interface ItemServices {

    @GET
    @Path("/categories")
    List<ItemCategory> getCategories();
}
