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

    /**
     * Counts all unsold items
     * @return Long value
     */
    Long countAllUnsoldItemsOfUser(Integer userId);

    /**
     * Load all finished items of a specific user
     *
     * @param user the onwer
     * @param firstResultIndex the first result index
     * @param pageSize the pageSize
     * @return
     */
    List<Item> loadFinishedItemsOfOwner(User user, int firstResultIndex, int pageSize);

    /**
     * Counts all finished items of user
     *
     * @return Long value
     */
    Long countAllFinishedItemsOfUser(Integer userId);

    /**
     * Load all items that a specific user had bidded on
     *
     * @param user the onwer
     * @param firstResultIndex the first result index
     * @param pageSize the pageSize
     * @return
     */
    List<Item> loadBiddedItemsByUser(User user, int firstResultIndex, int pageSize);

    /**
     * Counts all items that user had participated in auction
     * with a bid.
     *
     * @return Long value
     */
    Long countAllBiddedItemsByUser(Integer userId);

    /**
     * Load items by a serch string that might occur in
     * the name or description.
     *
     * @param searchBy the search string
     * @param firstResultIndex the first result index
     * @param pageSize the pageSize
     * @return
     */
    List<Item> loadByNameAndDescription(String searchBy, int firstResultIndex, int pageSize);

    /**
     * Counts all items according to search string
     *
     * @return Long value
     */
    Long countSearchedItems(String searchBy);
}
