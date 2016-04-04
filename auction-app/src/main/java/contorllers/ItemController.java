package contorllers;


import entity.Item;
import entity.ItemCategory;
import entity.ItemCondition;
import services.ItemService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


@RequestScoped
@ManagedBean(name = "itemController", eager = true)
public class ItemController {

    @Inject
    private ItemService itemService;

    private String itemName;

    private String description;

    private ItemCondition condition = ItemCondition.BRAND_NEW;

    private ItemCategory category;

    private Double startingPrice;

    private Integer daysTillEnd;

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
