package services;

import entity.ItemCategory;

import java.util.ArrayList;
import java.util.List;


public class ItemServices {

    public static List<ItemCategory> getCatagories() {

        return RestUtil.httpGet("/items/categories", ArrayList.class);

    }
}
