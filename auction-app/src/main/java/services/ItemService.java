package services;

import dto.ItemsWrapperListDto;
import entity.Item;
import entity.ItemCategory;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Service class that handles all the communication with server
 * in regard to Item data.
 *
 * Author: Ido Barash
 */
@Named
@ApplicationScoped
public class ItemService {

    // URLs
    private static final String GET_CATEGORIES_URL = "/items/categories";
    private static final String GET_ITEMS_URL = "/items/category/%s?pageNumber=%d&pageSize=%d";
    private static final String GET_ITEMS_VISITOR_URL = "/items?pageNumber=%d&pageSize=%d";
    private static final String POST_NEW_ITEM_URL = "/items/%d";
    private static final String GET_ITEM_URL = "/items/%d";


    /**
     * Get the list of categories from the server
     *
     * @return list of categories
     */
    public List<ItemCategory> getCatagories() {

        List<ItemCategory> result = new ArrayList<>();
        List<LinkedHashMap> categoriesAsMaps = RestUtil.httpGet(GET_CATEGORIES_URL, ArrayList.class);

        // Convert to ItemCategory class
        for (LinkedHashMap categoryMap : categoriesAsMaps) {

            ItemCategory category = new ItemCategory();

            category.setName((String) categoryMap.get("name"));
            category.setId((Integer) categoryMap.get("id"));

            result.add(category);
        }

        return result;
    }

    /**
     * Get Items from the server
     *
     * @param category the category if exists
     * @param pageNumber the current page to load the data
     * @param itemsPerPage the total items per page
     * @return list of items data
     */
    public static ItemsWrapperListDto getItems(String category, Integer pageNumber, int itemsPerPage) {

        if (category != null && "".equals(category) == false) {
            return RestUtil.httpGet(String.format(GET_ITEMS_URL, category,pageNumber, itemsPerPage), ItemsWrapperListDto.class);
        }

        return RestUtil.httpGet(String.format(GET_ITEMS_VISITOR_URL, pageNumber, itemsPerPage), ItemsWrapperListDto.class);
    }

    /**
     * Send a new item to sale to the
     * sever.
     *
     * @param item the item to sale.
     */
    public void postNewItem(Item item) {

        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);

        RestUtil.httpPostNoResponse(String.format(POST_NEW_ITEM_URL,userId), item);
    }

    /**
     * Load a single item from the server
     *
     * @param itemId the item to load
     * @return the item if was found
     */
    public Item loadItemFromServer(String itemId) {
        return RestUtil.httpGet(String.format(GET_ITEM_URL,itemId), Item.class);
    }
}
