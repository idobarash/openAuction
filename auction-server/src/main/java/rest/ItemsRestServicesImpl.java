package rest;

import dto.ItemsWrapperDto;
import entity.Item;
import entity.ItemCategory;
import services.ItemsBusinessService;

import javax.inject.Inject;
import java.util.List;

public class ItemsRestServicesImpl implements ItemsRestServices {

    @Inject
    private ItemsBusinessService itemsBusinessService;


    @Override
    public List<ItemCategory> getCategories() {
        return itemsBusinessService.getCategories();
    }

    @Override
    public ItemsWrapperDto getItems(String categoryName, int pageNumber, int pageSize) {

        ItemsWrapperDto result = new ItemsWrapperDto();

        // Count total items
        Long totalItemsCount = itemsBusinessService.countAllItemsByCategory(categoryName);
        result.setTotalItems(totalItemsCount);

        // Load items list
        List<Item> items = itemsBusinessService.getItemsByCategory(categoryName, pageNumber, pageSize);
        result.setItemsList(items);

        return result;
    }

    @Override
    public Boolean postNewItem(Integer userId, Item item) {
        return itemsBusinessService.saveNewItem(userId, item);
    }
}
