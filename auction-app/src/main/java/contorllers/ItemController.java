package contorllers;


import dto.FinishedAuctionDataDto;
import entity.Item;
import entity.ItemCategory;
import entity.User;
import enums.ItemCondition;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import services.ImagesUtil;
import services.ItemService;
import services.NavigationUtil;
import services.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Controller class which which handles the single item view (item.xhtml)
 *
 * Author: Ido Barash
 */
@ViewScoped
@ManagedBean(name = "itemController", eager = true)
public class ItemController extends BasicController {

    @Inject
    private ItemService itemService;

    private String currentItemId;

    private String itemName;

    private String description;

    private ItemCondition condition = ItemCondition.BRAND_NEW;

    private ItemCategory category;

    private Integer startingPrice;

    private Integer daysTillEnd;

    private Integer currentBid;

    private Integer bidsCounter;

    private Integer bidSum;

    private String message = "";

    private UploadedFile uploadedFile;

    private StreamedContent imageAsStream;

    private boolean auctionFinished;

    private boolean ownerViewsFinishedAuction = false;

    private User winner;

    @PostConstruct
    public void loadItemData() {

        if (currentItemId == null) {
            currentItemId = getRequestParameter("itemId");
            if (currentItemId == null || ((String) currentItemId).isEmpty()) {
                return;
            }
        }

        Item item = itemService.loadItemFromServer((String)currentItemId);

        this.itemName = item.getName();
        this.description = item.getDescription();
        this.condition = item.getCondition();
        this.category = item.getCategory();
        this.startingPrice = item.getStartPrice();
        this.bidsCounter = item.getBidsCounter();
        this.auctionFinished = item.isAuctionFinished();

        // Current bid
        this.currentBid = item.getCurrentBid();
        if (currentBid == null || currentBid == 0) {
            this.currentBid = item.getStartPrice();
        }

        // Calculate days till end of sale
        if (item.getEndDate() != null) {
            long millisTillEnd = item.getEndDate().getTime() - System.currentTimeMillis();
            daysTillEnd = (int) TimeUnit.DAYS.convert(millisTillEnd, TimeUnit.MILLISECONDS);
        }


        if (auctionFinished) {
            FinishedAuctionDataDto finishedAuctionDataDto = ItemService.getFinishedAuctionData(item.getId());

            if (finishedAuctionDataDto.getOwner() != null) {
                if (isLoggedIn()) {
                    Integer currentUserId = (Integer) SessionUtil.getSessionAttribute(SessionUtil.USER_ID);
                    if (currentUserId.equals(finishedAuctionDataDto.getOwner().getId())) {
                        ownerViewsFinishedAuction = true;
                    }
                }
            }

            winner = finishedAuctionDataDto.getWinner();
        }
    }

    public void postNewItem() {

        Item item = new Item();

        item.setCategory(category);
        item.setCondition(condition);
        item.setDescription(description);
        item.setStartPrice(startingPrice);
        item.setName(itemName);

        // Calculate end date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysTillEnd);
        item.setEndDate(calendar.getTime());

        Integer itemId = itemService.postNewItem(item);
        if (uploadedFile != null) {
            ImagesUtil.saveImage(uploadedFile, itemId);
        }

        NavigationUtil.navigateToRoot();
    }

    /**
     * Retrieve the item's image path
     * @return
     */
    public String getImagePath() {
        return ImagesUtil.getImagePath(currentItemId);
    }

    /**
     * Check if user is logged in.
     * @return true if user is logged in
     */
    public boolean isLoggedIn() {
        return SessionUtil.getSessionAttribute(SessionUtil.USERNAME) != null;
    }

    /**
     * Decide if the post new item button should
     * be displayed.
     *
     * @return true if the post new item button
     *                  should be displayed
     */
    public boolean isNewItemView() {

        String mode = getRequestParameter("mode");
        if (mode == null) {
            return  false;
        }

        return isLoggedIn() && mode.equals("new");
    }

    /**
     * Place a new bid on item.
     */
    public void placeBid() {
       if (bidSum > currentBid) {
           try {
               itemService.placeNewBid((String) currentItemId, bidSum);
               loadItemData();
           } catch (Exception e) {
               e.printStackTrace();
               message = e.getMessage();
           }
       }
    }

    /**
     * Is item in read-only mode
     * @return
     */
    public boolean isReadOnly() {

        if (isLoggedIn() == false) {
            return true;
        }

        String mode = getRequestParameter("mode");
        if (mode != null && mode.equals("new")) {
            return false;
        }

        return true;
    }

    public boolean isAllowContactBuyer() {
        String mode = getRequestParameter("mode");
        if (mode != null && mode.equals("myFinished")) {
            return true;
        }

        return false;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public Integer getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Integer startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Integer getDaysTillEnd() {
        return daysTillEnd;
    }

    public void setDaysTillEnd(Integer daysTillEnd) {
        this.daysTillEnd = daysTillEnd;
    }

    public List<ItemCondition> getConditionValues() {
        return Arrays.asList(ItemCondition.values());
    }

    public Integer getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Integer currentBid) {
        this.currentBid = currentBid;
    }

    public void setBidSum(Integer bidSum) {
        this.bidSum = bidSum;
    }

    public Integer getBidSum() {
        return bidSum;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public boolean isAuctionFinished() {
        return auctionFinished;
    }
}
