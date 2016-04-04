package dto;

import entity.Item;

import java.util.List;

public class ItemsWrapperDto {

    private Long totalItems;

    private List<Item> itemsList;

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }
}
