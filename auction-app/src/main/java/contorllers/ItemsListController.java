package contorllers;

import dto.ItemsWrapperListDto;
import services.ItemService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 * Controller class which handles the list items view (auctionItems.xhtml)
 *
 * Author: Ido Barash
 */
@RequestScoped
@ManagedBean(name = "itemsListController")
public class ItemsListController extends BasicController {

    private static String IMAGES_PATH = "/data/openu/img";
    private static int ITEMS_PER_PAGE = 9;
    private static int ITEMS_PER_PAGE_VISITOR = 6;

    @Inject
    private ItemService itemService;


    private String category;

    private Integer pageNumber = 0;

    /**
     * Load item list from the server
     *
     * @return items list for the current page
     */
    public ItemsWrapperListDto getItemsListwrapper() {

        String mode = getRequestParameter("mode");
        if ("myOngoing".equals(mode)) {
            return itemService.getItemsForUser(true, pageNumber, ITEMS_PER_PAGE);
        }
        else if ("myFinished".equals(mode)) {
            return itemService.getItemsForUser(false, pageNumber, ITEMS_PER_PAGE);
        }
        else if ("myBids".equals(mode)) {
            return itemService.getUserBidedItems(pageNumber, ITEMS_PER_PAGE);
        }
        else {
            // Extract category
            category = getRequestParameter("category");

            // Load items (9 for user or 6 for visitor)
            if (category == null || "".equals(category)) {
                return itemService.getItems(category, pageNumber, ITEMS_PER_PAGE_VISITOR);
            }

            return itemService.getItems(category, pageNumber, ITEMS_PER_PAGE);
        }
    }

    /**
     * Decide whether to display the big intro images or not.
     * The images should be displayed only on the first page when
     * navigating through items with no category filtering.
     *
     * @return true if images should be displayed.
     */
    public boolean getShowIntroImages() {

        category = getRequestParameter("category");

        if (pageNumber > 0) {
            return false;
        }

        return category == null || "".equals(category);
    }

}
