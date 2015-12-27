package rest;

import entity.ItemCategory;
import services.ItemsBusinessService;

import javax.inject.Inject;
import java.util.List;

public class ItemServicesImpl implements ItemServices {

    @Inject
    private ItemsBusinessService itemsBusinessService;


    @Override
    public List<ItemCategory> getCategories() {
        return itemsBusinessService.getCategories();
    }
}
