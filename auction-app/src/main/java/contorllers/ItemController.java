package contorllers;


import entity.Item;
import entity.ItemCategory;
import enums.ItemCondition;
import services.ItemService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
@ManagedBean(name = "itemController", eager = true)
public class ItemController extends BasicController {

    @Inject
    private ItemService itemService;

    private String itemName;

    private String description;

    private ItemCondition condition = ItemCondition.BRAND_NEW;

    private ItemCategory category;

    private Double startingPrice;

    private Integer daysTillEnd;

    private Double currentBid;

    private Integer bidsCounter;

    public void loadItemData() {

        Object itemId = getRequestParameter("itemId");

        if (itemId == null || ((String)itemId).isEmpty()) {
            return;
        }

        Item item = itemService.loadItemFromServer((String)itemId);

        this.itemName = item.getName();
        this.description = item.getDescription();
        this.condition = item.getCondition();
        this.category = item.getCategory();
        this.startingPrice = item.getStartPrice();
        this.currentBid = item.getCurrentBid();
        this.bidsCounter = item.getBidsCounter();

        // Calculate days till end of sale
        long millisTillEnd = item.getEndDate().getTime() - System.currentTimeMillis();
        daysTillEnd = (int)TimeUnit.DAYS.convert(millisTillEnd, TimeUnit.MILLISECONDS);
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

        itemService.postNewItem(item);
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

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
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
}
