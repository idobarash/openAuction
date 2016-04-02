package rest;

import entity.ItemCategory;
import utils.ItemsBusinessService;

import javax.inject.Inject;
import java.util.List;

public class ItemServicesImpl implements ItemServices {

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
}
