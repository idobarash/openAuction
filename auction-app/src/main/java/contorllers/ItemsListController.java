package contorllers;

import entity.Item;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@ManagedBean(name = "itemsListController")
public class ItemsListController {

    public List<Item> getItems() {
        //return ItemService.getItems();
        return null;
    }

}
