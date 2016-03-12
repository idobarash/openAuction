package services;

import entity.ItemCategory;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped
public class ItemServices {

    public List<ItemCategory> getCatagories() {

        return RestUtil.httpGet("/items/categories", ArrayList.class);

    }
}
