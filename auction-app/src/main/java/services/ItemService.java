package services;

import entity.Item;
import entity.ItemCategory;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped
public class ItemService {

    private static final String GET_CATEGORIES_URL = "/items/categories";
    private static final String GET_ITEMS = "/items/{%d}/category/{%s}";

    public List<ItemCategory> getCatagories() {

        return RestUtil.httpGet(GET_CATEGORIES_URL, ArrayList.class);

    }

    public static List<Item> getItems(ItemCategory category) {

        Integer userId = SessionService.getUserId();
        if (userId == null) {
            userId = -1;
        }

        String categoryString = "";
        if (category != null) {
            categoryString = category.getName();
        }

        return RestUtil.httpGet(String.format(GET_ITEMS, userId, categoryString), ArrayList.class);
    }
}
