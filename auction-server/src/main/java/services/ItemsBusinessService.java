package services;

import dao.ItemCategoryDao;
import dao.ItemDao;
import dao.UserDao;
import entity.ItemCategory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class ItemsBusinessService {

    @Inject
    private ItemCategoryDao itemCategoryDao;

    @Inject
    private ItemDao itemDao;

    @Inject
    private UserDao userDao;

    public List<ItemCategory> getCategories() {
        return itemCategoryDao.readAll();
    }

    public List<ItemCategory> getItemsForVisitor(String categoryName) {

        ItemCategory category = itemCategoryDao.readByName(categoryName);

        return itemDao.getItemsForVisitor(category);
    }

    public List<ItemCategory> getItemsByCategory(String categoryName) {
        return null;
    }
}
