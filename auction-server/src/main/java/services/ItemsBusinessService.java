package services;

import dao.BiddingDao;
import dao.ItemCategoryDao;
import dao.ItemDao;
import dao.UserDao;
import entity.Bidding;
import entity.Item;
import entity.ItemCategory;
import entity.User;
import exception.AuctionException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
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
        int firstResultIndex = (pageNumber - 1) * pageSize;
        if (firstResultIndex < 0) {
            firstResultIndex = 0;
        }

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
     * @return true if success
     */
    public Boolean saveNewItem(Integer userId, Item item) {

        // Validate user
        User user = userDao.read(userId, User.class);
        if (user == null) {
            throw new AuctionException("User " + userId + " does not exist");
        }

        // Update item system data
        item.setCreationDate(new Date());
        item.setIsSold(false);

        // Add item to user
        user.getItems().add(item);

        // Update DB
        itemDao.create(item);
        userDao.update(user);

        return true;
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
}
