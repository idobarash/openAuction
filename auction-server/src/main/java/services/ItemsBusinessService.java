package services;

import dao.BiddingDao;
import dao.ItemCategoryDao;
import dao.ItemDao;
import dao.UserDao;
import dto.ItemsWrapperListDto;
import entity.Bidding;
import entity.Item;
import entity.ItemCategory;
import entity.User;
import exception.AuctionException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service class to handle all the logic
 * regarding the items.
 *
 * Author: Ido Barash
 */
@Named
@Stateless
public class ItemsBusinessService {

    @Inject
    private ItemCategoryDao itemCategoryDao;

    @Inject
    private ItemDao itemDao;

    @Inject
    private UserDao userDao;

    @Inject
    private BiddingDao biddingDao;

    /**
     * @return a list of all the catagories
     */
    public List<ItemCategory> getCategories() {
        return itemCategoryDao.readAll();
    }


    /**
     * Load items from the DB.
     *
     * @param categoryId the category if exists
     * @param pageNumber the page to load
     * @param pageSize total item in page
     * @return list of items
     */
    public List<Item> getItems(Integer categoryId, int pageNumber, int pageSize) {

        // Calculate requested page first item index.
        int firstResultIndex = getFirstResultIndex(pageNumber, pageSize);

        // Load by category
        if (categoryId == null || categoryId == 0) {
            return itemDao.loadUnsoldItems(firstResultIndex, pageSize);
        }

        // Load without category
        return itemDao.loadUnsoldItemsByCategoryName(categoryId, firstResultIndex, pageSize);
    }

    /**
     * Save new user to DB
     *
     * @param userId the owner
     * @param item the item to save
     * @return the item id
     */
    public Integer saveNewItem(Integer userId, Item item) {

        // Validate user
        User user = userDao.read(userId, User.class);
        if (user == null) {
            throw new AuctionException("User " + userId + " does not exist");
        }

        // Update item system data
        item.setCreationDate(new Date());
        item.setAuctionFinished(false);

        // Add item to user
        user.getItems().add(item);

        // Update DB
        itemDao.create(item);
        userDao.update(user);

        return item.getId();
    }

    /**
     * Count all items
     *
     * @param categoryId slice by category if exists
     * @return The number of items
     */
    public Long countAllItemsByCategory(Integer categoryId) {

        if (categoryId == null || categoryId == 0) {
            return itemDao.countAllUnsoldItems();
        }

        return itemDao.countAllUnsoldItemsByCategory(categoryId);
    }

    /**
     * Read an item from the DB.
     *
     * @param itemId the id of the item to load
     * @return the item if exists
     */
    public Item loadItemById(Integer itemId) {
        return itemDao.read(itemId, Item.class);
    }

    /**
     * Create a new bid.
     * First, check that the bid sum is higher than the current bid sum.
     * Than create the bid, save it and update the item's record too.
     *
     * @param itemId the item id to bid on
     * @param userId the user id
     * @param bidSum the bid sum
     * @return true if success
     */
    public Boolean placeNewBidOnItem(Integer itemId, Integer userId, Integer bidSum) {

        // Load item and validate sums
        Item item = itemDao.read(itemId, Item.class);
        if (item.getCurrentBid() > bidSum) {
            throw new AuctionException("You were already ouybidded!!!");
        }

        // Load user
        User user = userDao.read(userId, User.class);

        // Create bidding
        Bidding biddingData = new Bidding();
        biddingData.setBidSum(bidSum);
        biddingData.setBiddingUser(user);
        biddingData.setItem(item);
        biddingDao.create(biddingData);

        // Update item
        item.setCurrentBid(bidSum);
        item.setBidsCounter(item.getBidsCounter() + 1);
        itemDao.update(item);

        return true;
    }

    /**
     * Load a page of ongoing auction items for a spcific user.
     * @param userId the user id
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<Item> getOngoingAuctionItems(Integer userId, int pageNumber, int pageSize) {

        User user = userDao.read(userId, User.class);
        if (user == null) {
            return new ArrayList<>();
        }

        // Calculate requested page first item index.
        int firstResultIndex = getFirstResultIndex(pageNumber, pageSize);

        return itemDao.loadUnsoldItemsOfOwner(user, firstResultIndex, pageSize);
    }

    public Long countOnGoingAuctionsForUser(Integer userId) {
        return itemDao.countAllUnsoldItemsOfUser(userId);
    }

    public List<Item> getFinishedAuctionItems(Integer userId, int pageNumber, int pageSize) {
        User user = userDao.read(userId, User.class);
        if (user == null) {
            return new ArrayList<>();
        }

        // Calculate requested page first item index.
        int firstResultIndex = getFirstResultIndex(pageNumber, pageSize);

        // Set items that are not already sold as sold.
        List<Item> finishedItems = itemDao.loadFinishedItemsOfOwner(user, firstResultIndex, pageSize);
        for (Item item : finishedItems) {
            if (item.isAuctionFinished() == false) {
                item.setAuctionFinished(true);
                itemDao.update(item);
            }
        }

        return finishedItems;
    }

    public Long countFinishedAuctionsForUser(Integer userId) {
        return itemDao.countAllFinishedItemsOfUser(userId);
    }

    public List<Item> getItemsBiddedByUser(Integer userId, int pageNumber, int pageSize) {

        User user = userDao.read(userId, User.class);
        if (user == null) {
            return new ArrayList<>();
        }

        // Calculate requested page first item index.
        int firstResultIndex = getFirstResultIndex(pageNumber, pageSize);

        return itemDao.loadBiddedItemsByUser(user, firstResultIndex, pageSize);
    }

    public Long countItemsBiddedByUser(Integer userId) {
        return itemDao.countAllBiddedItemsByUser(userId);
    }

    /**
     * Helper method to calculate the first index of the current page.
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    private int getFirstResultIndex(int pageNumber, int pageSize) {
        int firstResultIndex = (pageNumber - 1) * pageSize;
        if (firstResultIndex < 0) {
            firstResultIndex = 0;
        }
        return firstResultIndex;
    }
}
