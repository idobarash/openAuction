package dao;

import dao.base.GenericDao;
import entity.ItemCategory;

import java.util.List;

/**
 * Data Access Object for category
 * Handles special category oriented DB operations
 *
 * Author: Ido Barash
 */
public interface ItemCategoryDao extends GenericDao<ItemCategory> {

    /**
     * Get all categories
     * @return list of all the categories from the DB
     */
    List<ItemCategory> readAll();

    /**
     * Get a specific category by name
     * @param categoryName the name of the category to find.
     * @return the category
     */
    ItemCategory readByName(String categoryName);


}
