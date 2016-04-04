package dto;

import entity.Item;

import java.util.List;

/**
 * Wrapper class for items
 *
 * Contains the items to show in one page plus the total
 * number of items.
 *
 * Author: Ido Barash
 */
public class ItemsWrapperListDto {

    private Long totalItems;

    private List<Item> itemList;

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
