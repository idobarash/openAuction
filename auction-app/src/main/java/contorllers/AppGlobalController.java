package contorllers;


import entity.ItemCategory;
import utils.ItemService;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * View handler class to handle the header
 */
@Named
@SessionScoped
public class AppGlobalController implements Serializable {

    private String searchText = "Type item to search";

    private List<ItemCategory> categories;

    @Inject
    private ItemService itemServices;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    /**
     * Retrieve the categories from the server.
     *
     * @return list of categories
     */
    public List<ItemCategory> getCategoriesList() {

        if (categories == null || categories.isEmpty()) {
            categories = itemServices.getCatagories();
        }

        return categories;
    }
}