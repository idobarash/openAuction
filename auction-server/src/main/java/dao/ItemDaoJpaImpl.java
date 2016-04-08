package dao;

import dao.base.AbstractJpaDao;
import entity.Item;
import entity.User;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * JPA DAO implementation to handle Items entities.
 *
 * Author: Ido Barash
 */
@Named
public class ItemDaoJpaImpl extends AbstractJpaDao<Item> implements ItemDao {

    @Override
    public List<Item> loadUnsoldItemsByCategoryName(Integer categoryId, int firstResultIndex, Integer itemsPerPage) {

        Query query = entityManager.createQuery(
                "SELECT i FROM  Item AS i WHERE i.category.id = :categoryId AND i.isSold = false" +
                        " AND endDate > :today ORDER BY i.creationDate DESC");

        query.setParameter("categoryId", categoryId);
        query.setParameter("today", new Date());
        query.setFirstResult(firstResultIndex);
        query.setMaxResults(itemsPerPage);

        return query.getResultList();
    }

    @Override
    public List<Item> loadUnsoldItems(int firstResultIndex, int itemsPerPage) {

        Query query = entityManager.createQuery("SELECT i FROM  Item i WHERE endDate > :today " +
                                                "ORDER BY i.creationDate DESC");

        query.setParameter("today", new Date());
        query.setFirstResult(firstResultIndex);
        query.setMaxResults(itemsPerPage);

        return query.getResultList();
    }

    @Override
    public Long countAllUnsoldItemsByCategory(Integer categoryId) {

        Query query = entityManager.createQuery("SELECT COUNT(i) FROM Item AS i WHERE i.category.id = :categoryId " +
                                                    "AND i.isSold = false AND endDate > :today");
        query.setParameter("categoryId", categoryId);
        query.setParameter("today", new Date());

        return (Long) query.getSingleResult();
    }

    @Override
    public Long countAllUnsoldItems() {

        Query query  = entityManager.createQuery("SELECT COUNT(i) FROM Item i WHERE endDate > :today");
        query.setParameter("today", new Date());

        return (Long) query.getSingleResult();
    }

    @Override
    public List<Item> loadUnsoldItemsOfOwner(User user, int firstResultIndex, int pageSize) {

        Query query = entityManager.createQuery("SELECT i FROM User AS u LEFT JOIN u.items AS i " +
                                                "WHERE i.isSold = false AND i.endDate > :today and u.id = :userId " +
                                                "ORDER BY i.creationDate");
        query.setParameter("today", new Date());
        query.setParameter("userId", user.getId());
        query.setFirstResult(firstResultIndex);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Long countAllUnsoldItemsOfUser(Integer userId) {
        Query query = entityManager.createQuery("SELECT COUNT(i) FROM User AS u LEFT JOIN u.items AS i " +
                "WHERE i.isSold = false AND i.endDate > :today and u.id = :userId ");

        query.setParameter("today", new Date());
        query.setParameter("userId", userId);

        return (Long) query.getSingleResult();
    }

    @Override
    public List<Item> loadFinishedItemsOfOwner(User user, int firstResultIndex, int pageSize) {
        Query query = entityManager.createQuery("SELECT i FROM User AS u LEFT JOIN u.items AS i " +
                "WHERE i.endDate <= :today and u.id = :userId  ORDER BY i.endDate DESC");
        query.setParameter("today", new Date());
        query.setParameter("userId", user.getId());
        query.setFirstResult(firstResultIndex);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Long countAllFinishedItemsOfUser(Integer userId) {
        Query query = entityManager.createQuery("SELECT COUNT(i) FROM User AS u LEFT JOIN u.items AS i " +
                "WHERE i.endDate < :today and u.id = :userId ");

        query.setParameter("today", new Date());
        query.setParameter("userId", userId);

        return (Long) query.getSingleResult();
    }

    @Override
    public List<Item> loadBiddedItemsByUser(User user, int firstResultIndex, int pageSize) {
        Query query = entityManager.createQuery("SELECT i FROM Bidding AS b JOIN b.biddingUser AS u JOIN b.item as i " +
                                                "WHERE u.id = :userId ORDER BY i.creationDate");
        query.setParameter("userId", user.getId());
        query.setFirstResult(firstResultIndex);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Long countAllBiddedItemsByUser(Integer userId) {

        Query query = entityManager.createQuery("SELECT count(i) FROM Bidding AS b JOIN b.biddingUser AS u JOIN b.item as i " +
                "WHERE u.id = :userId ORDER BY i.creationDate");

        query.setParameter("userId", userId);

        return (Long) query.getSingleResult();
    }


}
