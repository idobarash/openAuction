package dao;

import dao.base.GenericDao;
import entity.Bidding;
import entity.Item;

import java.util.List;

/**
 * Data Access Object for Bidding
 * Handles special Bidding oriented DB operations
 *
 * Author: Ido Barash
 */
public interface BiddingDao extends GenericDao<Bidding> {

    /**
     * Find the highest bid for item.
     *
     * @param itemId
     * @return the max bid for item
     */
    Bidding getMaxBidForItem(Integer itemId);
}
