package services;

import dao.ItemCategoryDao;
import dao.ItemDao;
import dao.UserDao;
import entity.Item;
import entity.ItemCategory;
import entity.User;
import exception.AuctionException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Service class to handle all the logic
 * regarding the items.
 */
@Named
@Stateless
public class ItemsBusinessService {

    @Inject
    private ItemCategoryDao itemCategoryDao;

    @Inject
    private ItemDao itemDao;

    @Inject
    private UserDao userDao;

    /**
     * @return a list of all the catagories
     */
    public List<ItemCategory> getCategories() {
        return itemCategoryDao.readAll();
    }

    /**
     * Return last items for a visitor per a specific category
     *
     * @param categoryName the category
     * @return list of last items in that category
     */
    public List<ItemCategory> getItemsForVisitor(String categoryName) {

        ItemCategory category = itemCategoryDao.readByName(categoryName);

        return itemDao.getItemsForVisitor(category);
    }

    public List<ItemCategory> getItemsByCategory(String categoryName) {
        return null;
    }

    /**
     * Save new user to DB
     *
     * @param userId the owner
     * @param item the item to save
     * @return true if success
     */
    public Boolean saveNewItem(Integer userId, Item item) {

        // Validate user
        User user = userDao.read(userId, User.class);
        if (user == null) {
            throw new AuctionException("User " + userId + " does not exist");
        }

        // Update item system data
        item.setCreationDate(new Date());
        item.setIsSold(false);

        // Add item to user
        user.getItems().add(item);

        // Update DB
        itemDao.create(item);
        userDao.update(user);

        return true;
    }
}
