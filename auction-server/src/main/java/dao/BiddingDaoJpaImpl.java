package dao;

import dao.base.AbstractJpaDao;
import entity.Bidding;
import entity.Item;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

/**
 * JPA DAO implementation to handle Bidding entities.
 *
 * Author: Ido Barash
 */
@Named
public class BiddingDaoJpaImpl extends AbstractJpaDao<Bidding> implements BiddingDao {

    @Override
    public Bidding getMaxBidForItem(Integer itemId) {
        try {
            Query query = entityManager.createQuery("SELECT b FROM Bidding b JOIN b.biddingUser u  WHERE b.item.id = :itemId ORDER BY b.bidSum DESC")
                    .setParameter("itemId", itemId)
                    .setMaxResults(1);

            return (Bidding) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
