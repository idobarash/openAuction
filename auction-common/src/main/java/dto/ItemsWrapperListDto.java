package dto;

import entity.Item;

import java.util.List;

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
