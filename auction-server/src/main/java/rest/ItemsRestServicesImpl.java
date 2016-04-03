package rest;

import entity.Item;
import entity.ItemCategory;
import services.ItemsBusinessService;

import javax.inject.Inject;
import java.util.List;
import java.util.jar.Pack200;

public class ItemsRestServicesImpl implements ItemsRestServices {

    @Inject
    private ItemsBusinessService itemsBusinessService;


    @Override
    public List<ItemCategory> getCategories() {
        return itemsBusinessService.getCategories();
    }

    @Override
    public List<ItemCategory> getItems(Integer userId, String categoryName) {

        if (userId == null) {
            return itemsBusinessService.getItemsForVisitor(categoryName);
        }

        return itemsBusinessService.getItemsByCategory(categoryName);
    }

    @Override
    public Boolean postNewItem(Integer userId, Item item) {
        return itemsBusinessService.saveNewItem(userId, item);
    }
}
