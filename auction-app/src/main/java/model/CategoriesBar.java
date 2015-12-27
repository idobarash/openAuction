package model;

import entity.ItemCategory;
import services.ItemServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class CategoriesBar {

    private List<ItemCategory> itemCategories;

    private Integer currentCategroyIndex;

    public void init() {
        if (itemCategories == null) {
            itemCategories = ItemServices.getCatagories();
            currentCategroyIndex = 0;
        }
    }


    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(List<ItemCategory> itemCategories) {
        this.itemCategories = itemCategories;
    }

    public Integer getCurrentCategroyIndex() {
        return currentCategroyIndex;
    }

    public void setCurrentCategroyIndex(Integer currentCategroyIndex) {
        this.currentCategroyIndex = currentCategroyIndex;
    }
}
