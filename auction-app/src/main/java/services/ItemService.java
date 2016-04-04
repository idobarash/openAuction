package services;

import dto.ItemsWrapperListDto;
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
    private static final String GET_ITEMS_URL = "/items/category/%s?pageNumber=%d&pageSize=%d";
    private static final String GET_ITEMS_VISITOR_URL = "/items?pageNumber=%d&pageSize=%d";
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

    public static ItemsWrapperListDto getItems(String category, Integer pageNumber, int itemsPerPage) {

        if (category != null && "".equals(category) == false) {
            return RestUtil.httpGet(String.format(GET_ITEMS_URL, category,pageNumber, itemsPerPage), ItemsWrapperListDto.class);
        }

        return RestUtil.httpGet(String.format(GET_ITEMS_VISITOR_URL, pageNumber, itemsPerPage), ItemsWrapperListDto.class);
    }

    public void postNewItem(Item item) {

        Integer userId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);

        RestUtil.httpPostNoResponse(String.format(POST_NEW_ITEM_URL,userId), item);
    }
}
