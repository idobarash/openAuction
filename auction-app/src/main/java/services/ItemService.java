package services;

import entity.Item;
import entity.ItemCategory;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Named
@ApplicationScoped
public class ItemService {

    private static final String GET_CATEGORIES_URL = "/items/categories";
    private static final String GET_ITEMS_URL = "/items/{%d}/category/{%s}";
    private static final String POST_NEW_ITEM_URL = "/items/%d";

    public List<ItemCategory> getCatagories() {

        List<ItemCategory> result = new ArrayList<>();

        List<LinkedHashMap> categoriesAsMaps = RestUtil.httpGet(GET_CATEGORIES_URL, ArrayList.class);
        for (LinkedHashMap categoryMap : categoriesAsMaps) {

            ItemCategory category = new ItemCategory();

            category.setName((String) categoryMap.get("name"));
            category.setId((Integer) categoryMap.get("id"));

            result.add(category);
        }

        return result;
    }

    public static List<Item> getItems(ItemCategory category) {

        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);
        if (userId == null) {
            userId = -1;
        }

        String categoryString = "";
        if (category != null) {
            categoryString = category.getName();
        }

        return RestUtil.httpGet(String.format(GET_ITEMS_URL, userId, categoryString), ArrayList.class);
    }

    public void postNewItem(Item item) {

        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);

        RestUtil.httpPostNoResponse(String.format(POST_NEW_ITEM_URL,userId), item);
    }
}
