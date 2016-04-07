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

    // URLs - items
    private static final String GET_CATEGORIES_URL = "/items/categories";
    private static final String GET_ITEMS_URL = "/items/category/%s?pageNumber=%d&pageSize=%d";
    private static final String GET_ITEMS_VISITOR_URL = "/items?pageNumber=%d&pageSize=%d";
    private static final String POST_NEW_ITEM_URL = "/items/%d";
    private static final String POST_NEW_BID_ON_ITEM_URL = "/items/%s/user/%d/bid/%d";
    private static final String GET_ITEM_URL = "/items/%s";

    // URLs - user filtering
    private static final String GET_USER_ONGOING_ITEMS_URL = "/users/%d/ongoing?pageNumber=%d&pageSize=%d";
    private static final String GET_USER_FINISHED_ITEMS_URL = "/users/%d/finished?pageNumber=%d&pageSize=%d";
    private static final String GET_USER_BIDS_URL = "/users/%d/bids?pageNumber=%d&pageSize=%d";


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
    public Integer postNewItem(Item item) {
        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);
        return RestUtil.httpPost(String.format(POST_NEW_ITEM_URL,userId), Integer.class, item);
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

    /**
     * Send a new bid to server
     * @param itemId
     * @param bidSum
     */
    public void placeNewBid(String itemId, Integer bidSum) {

        try {
            Object userId = SessionUtil.getSessionAttribute(SessionUtil.USER_ID);
            Boolean success = RestUtil.httpPost(String.format(POST_NEW_BID_ON_ITEM_URL,itemId, userId, bidSum), Boolean.class, null);
        }
        catch (Exception e) {

        }
    }

    /**
     * Get user Items
     *
     * @param isOngoing whether to get only ongoing or finished
     * @param pageNumber the current page
     * @param itemsPerPage items per page
     * @return Wrapper object containing the count and the list.
     */
    public ItemsWrapperListDto getItemsForUser(boolean isOngoing, Integer pageNumber, int itemsPerPage) {

        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);

        if (isOngoing) {
            return RestUtil.httpGet(String.format(GET_USER_ONGOING_ITEMS_URL,userId, pageNumber, itemsPerPage), ItemsWrapperListDto.class);
        }

        return RestUtil.httpGet(String.format(GET_USER_FINISHED_ITEMS_URL,userId, pageNumber, itemsPerPage), ItemsWrapperListDto.class);
    }

    /**
     * Get user bidded items
     *
     * @param pageNumber the current page
     * @param itemsPerPage items per page
     * @return Wrapper object containing the count and the list.
     */
    public ItemsWrapperListDto getUserBidedItems(Integer pageNumber, int itemsPerPage) {

        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);

        return RestUtil.httpGet(String.format(GET_USER_BIDS_URL,userId, pageNumber, itemsPerPage), ItemsWrapperListDto.class);
    }
}
