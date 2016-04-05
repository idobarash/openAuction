package dao;

import dao.base.GenericDao;
import entity.Item;
import entity.User;

import java.util.List;

/**
 * Data Access Object for item
 * Handles special items oriented DB operations
 *
 * Author: Ido Barash
 */
public interface ItemDao extends GenericDao<Item> {

    /**
     * Load all unsold items by a category name and pages.
     *
     * @param categoryId the category
     * @param firstResultIndex the page first item
     * @param itemsPerPage total items per page
     * @return list of items
     */
    List<Item> loadUnsoldItemsByCategoryName(Integer categoryId, int firstResultIndex, Integer itemsPerPage);

    /**
     * Load all unsold items
     *
     * @param firstResultIndex the page first item
     * @param pageSize total items per page
     * @return list of items
     */
    List<Item> loadUnsoldItems(int firstResultIndex, int pageSize);

    /**
     * Counts all unsold items per category
     * @param categoryId the category
     * @return Long value
     */
    Long countAllUnsoldItemsByCategory(Integer categoryId);

    /**
     * Counts all unsold items
     * @return Long value
     */
    Long countAllUnsoldItems();

    /**
     * Load all unsold items of a specific user
     *
     * @param user the onwer
     * @param firstResultIndex the first result index
     * @param pageSize the pageSize
     * @return
     */
    List<Item> loadUnsoldItemsOfOwner(User user, int firstResultIndex, int pageSize);


    Long countAllUnsoldItemsOfUser(Integer userId);

    List<Item> loadFinishedItemsOfOwner(User user, int firstResultIndex, int pageSize);

    Long countAllFinishedItemsOfUser(Integer userId);

    List<Item> loadBiddedItemsByUser(User user, int firstResultIndex, int pageSize);

    Long countAllBiddedItemsByUser(Integer userId);
}
