package contorllers;

import dto.ItemsWrapperListDto;
import entity.Item;
import entity.ItemCategory;
import services.ItemService;
import services.RequestExtractorUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@ManagedBean(name = "itemsListController")
public class ItemsListController {

    private static String IMAGES_PATH = "/data/openu/img";
    private static int ITEMS_PER_PAGE = 9;
    private static int ITEMS_PER_PAGE_VISITOR = 6;


    @Inject
    private ItemService itemService;

    private String category;

    private Integer pageNumber = 0;


    public ItemsWrapperListDto getItemsListwrapper() {

        category = RequestExtractorUtil.getRequestParameterValue("category");

        // Load items (9 for user or 6 for visitor)
        if (category == null || "".equals(category)) {
            return itemService.getItems(category, pageNumber, ITEMS_PER_PAGE_VISITOR);
        }

        return itemService.getItems(category, pageNumber, ITEMS_PER_PAGE);
    }

    public boolean showIntroImages() {

        if (pageNumber > 0) {
            return false;
        }

        return category == null || "".equals(category);
    }

}
