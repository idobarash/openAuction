package contorllers;

import services.NavigationUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class which handles the User view (user.xhtml)
 *
 * Author: Ido Barash
 */
@ViewScoped
@ManagedBean(name = "searchController", eager = false)
public class SearchController extends BasicController {

    private String searchString;

    public void search() {
        Map<String, String> parametes = new HashMap<>();
        parametes.put("searchBy", searchString);
        NavigationUtil.navigetToItemsPage("search",null,0, parametes);
    }


    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
