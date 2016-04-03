package contorllers;


import entity.ItemCategory;
import entity.ItemCondition;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@ManagedBean(name = "itemController", eager = true)
public class ItemController {

    private String itemName;

    private String description;

    private ItemCondition condition = ItemCondition.BRAND_NEW;

    private ItemCategory category;

    private Double startingPrice;

    private Integer daysTillEnd;


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

    public void newItem() {
        return ;
    }

    public List<ItemCondition> getConditionValues() {
        return Arrays.asList(ItemCondition.values());
    }
}
