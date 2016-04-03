package contorllers;


import entity.ItemCategory;
import services.ItemService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * View handler class to handle the header
 */
@SessionScoped
@ManagedBean(name = "appGlobalController", eager = true)
public class AppGlobalController implements Serializable {

    private String searchText = "Type item to search";

    private List<ItemCategory> categories;

    @Inject
    private ItemService itemServices;


    @PostConstruct
    public void init() {
        categories = itemServices.getCatagories();
    }

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
        return categories;
    }

    public Object getCategoryById(String categoryId) {
        for (ItemCategory category : getCategoriesList()) {
            if (category.getName().toString().equals(categoryId)) {
                return category;
            }
        }

        return categories.get(0);
    }
}
