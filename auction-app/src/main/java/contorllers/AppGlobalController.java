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
 * Controller which is globally used through the entire session.
 * Contains global data such as the categories, texts and stuff.
 *
 * Author: Ido Barash
 */
@SessionScoped
@ManagedBean(name = "appGlobalController", eager = true)
public class AppGlobalController extends BasicController implements Serializable {

    @Inject
    private ItemService itemServices;

    private String searchText = "Type item to search";

    private List<ItemCategory> categories;

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

    /**
     * Find a category by a given ID.
     *
     * @param categoryId the category ID to find
     * @return the category
     */
    public Object getCategoryById(String categoryId) {

        for (ItemCategory category : getCategoriesList()) {
            if (category.getName().toString().equals(categoryId)) {
                return category;
            }
        }

        return categories.get(0);
    }
}
