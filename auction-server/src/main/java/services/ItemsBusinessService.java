package services;

import dao.ItemCategoryDao;
import entity.ItemCategory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class ItemsBusinessService {

    @Inject
    private ItemCategoryDao itemCategoryDao;

    public List<ItemCategory> getCategories() {
        return itemCategoryDao.readAll();
    }
}
