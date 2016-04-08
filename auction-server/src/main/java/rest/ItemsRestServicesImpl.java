package rest;

import dto.FinishedAuctionDataDto;
import dto.ItemsWrapperListDto;
import entity.Item;
import entity.ItemCategory;
import services.ItemsBusinessService;

import javax.inject.Inject;
import java.util.List;

/**
 * Items operations REST service implementation.
 * Handles request-response operations for items data
 *
 * Author: Ido Barash
 */
public class ItemsRestServicesImpl implements ItemsRestServices {

    @Inject
    private ItemsBusinessService itemsBusinessService;


    @Override
    public List<ItemCategory> getCategories() {
        return itemsBusinessService.getCategories();
    }

    @Override
    public ItemsWrapperListDto getItems(Integer categoryId, int pageNumber, int pageSize) {

        ItemsWrapperListDto result = new ItemsWrapperListDto();

        // Count total items
        Long totalItemsCount = itemsBusinessService.countAllItemsByCategory(categoryId);
        result.setTotalItems(totalItemsCount);

        // Load items list
        List<Item> itemsList = itemsBusinessService.getItems(categoryId, pageNumber, pageSize);
        result.setItemList(itemsList);

        return result;
    }

    @Override
    public ItemsWrapperListDto getItems(int pageNumber, int pageSize) {
        return getItems(null, pageNumber, pageSize);
    }

    @Override
    public Integer postNewItem(Integer userId, Item item) {
        return itemsBusinessService.saveNewItem(userId, item);
    }

    @Override
    public Item getItem(Integer itemId) {
        return itemsBusinessService.loadItemById(itemId);
    }

    @Override
    public Boolean placeNewBid(Integer itemId, Integer userId, Integer bidSum) {
        return itemsBusinessService.placeNewBidOnItem(itemId, userId, bidSum);
    }

    @Override
    public FinishedAuctionDataDto getFinishedAuctionData(Integer itemId) {
        return itemsBusinessService.getFinishedAuctionItemData(itemId);
    }
}
