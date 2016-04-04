package contorllers;

import entity.Item;
import entity.ItemCategory;
import services.ItemService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@ManagedBean(name = "itemsListController")
public class ItemsListController {

    private static String IMAGES_PATH = "/data/openu/img";
    private static int ITEMS_PER_PAGE = 9;


    @Inject
    private ItemService itemService;

    private ItemCategory category;

    private Integer pageNumber = 0;


    public List<List<Item>> getItems() {
        List<Item> items = itemService.getItems(category, pageNumber, ITEMS_PER_PAGE);

        List<List<Item>> result = new ArrayList<>();

        if (items != null && !items.isEmpty()) {

            List<Item> currentList = new ArrayList<>();
            int count = 0;
            for (Item item : items) {
                currentList.add(item);
                count++;
                if (count == 3) {
                    result.add(currentList);
                    currentList = new ArrayList<>();
                    count = 0;
                }
            }
        }

        return result;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
