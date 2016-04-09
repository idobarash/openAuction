package contorllers;

import dto.ItemsWrapperListDto;
import services.ItemService;
import services.NavigationUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.regex.Matcher;

/**
 * Controller class which handles the list items view (auctionItems.xhtml)
 *
 * Author: Ido Barash
 */
@ViewScoped
@ManagedBean(name = "itemsListController")
public class ItemsListController extends BasicController {

    private static String IMAGES_PATH = "/data/openu/img";
    private static int ITEMS_PER_PAGE = 9;
    private static int ITEMS_PER_PAGE_VISITOR = 6;

    @Inject
    private ItemService itemService;

    private ItemsWrapperListDto items;

    private String category;

    private Integer pageNumber = 1;

    private String mode;

    private boolean paginationRequired = false;

    /**
     * Load item list from the server
     *
     * @return items list for the current page
     */
    @PostConstruct
    public void init() {

        // Extract page
        String pageNumberAsString = getRequestParameter("page");
        if (pageNumberAsString != null && pageNumberAsString.isEmpty() == false && pageNumberAsString.matches("-?\\d+(\\.\\d+)?"))  {
            pageNumber = Integer.valueOf(pageNumberAsString);
        }

        // Extract mode
        if (mode == null) {
            mode = getRequestParameter("mode");
        }

        // Load according to mode
        if ("myOngoing".equals(mode)) {
            items = itemService.getItemsForUser(true, pageNumber, ITEMS_PER_PAGE);
        }
        else if ("myFinished".equals(mode)) {
            items = itemService.getItemsForUser(false, pageNumber, ITEMS_PER_PAGE);
        }
        else if ("myBids".equals(mode)) {
            items = itemService.getUserBidedItems(pageNumber, ITEMS_PER_PAGE);
        }
        else if ("search".equals(mode)) {
            String serachBy = getRequestParameter("searchBy");
            items = itemService.getItemsBySearchString(serachBy, pageNumber, ITEMS_PER_PAGE);
        }
        else {
            // Extract category
            if (category == null) {
                category = getRequestParameter("category");
            }

            // Load items (9 for user or 6 for visitor)
            if ((category == null || "".equals(category)) && (pageNumber == 1)) {
                items = itemService.getItems(category, pageNumber, ITEMS_PER_PAGE_VISITOR);
                if (items.getTotalItems() > ITEMS_PER_PAGE_VISITOR) {
                    paginationRequired = true;
                }
            } else {
                items = itemService.getItems(category, pageNumber, ITEMS_PER_PAGE);
            }
        }

        // Pagination ?
        if (items != null && items.getTotalItems() != null && items.getTotalItems() > ITEMS_PER_PAGE) {
            paginationRequired = true;
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

        String category = getRequestParameter("category");
        String mode = getRequestParameter("mode");

        if (pageNumber > 1) {
            return false;
        }

        if (mode != null && "".equals(mode) == false) {
            return false;
        }

        return category == null || "".equals(category);
    }

    /**
     * Move to next page
     */
    public void nextPage() {
        pageNumber += 1;
        NavigationUtil.navigetToItemsPage(mode, category, pageNumber);
    }

    /**
     * Move to previous page
     */
    public void previousPage() {

        pageNumber = pageNumber - 1;
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        NavigationUtil.navigetToItemsPage(mode, category, pageNumber);
    }

    /**
     * Display pagination icons
     * @return
     */
    public boolean isPaginationRequired() {
        return paginationRequired;
    }

    public ItemsWrapperListDto getItems() {
        return items;
    }

    public String getMode() {
        return mode;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }
}
