package viewHandlers;

import entity.Item;
import services.ItemService;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

@Named
@SessionScoped
public class ItemsView {

    public List<Item> getItems() {
        //return ItemService.getItems();
        return null;
    }

}
