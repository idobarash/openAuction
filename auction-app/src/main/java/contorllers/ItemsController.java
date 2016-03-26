package contorllers;

import entity.Item;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

@Named
@SessionScoped
public class ItemsController {

    public List<Item> getItems() {
        //return ItemService.getItems();
        return null;
    }

}
