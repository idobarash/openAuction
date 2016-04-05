package dao;

import dao.base.AbstractJpaDao;
import entity.Item;

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

        Query query = entityManager.createQuery("SELECT i FROM  Item i WHERE endDate > :today ORDER BY i.creationDate DESC");

        query.setParameter("today", new Date());
        query.setFirstResult(firstResultIndex);
        query.setMaxResults(itemsPerPage);

        return query.getResultList();
    }

    @Override
    public Long countAllUnsoldItemsByCategory(Integer categoryId) {

        Query query = entityManager.createQuery("SELECT COUNT(i) FROM Item AS i WHERE i.category.id = :categoryId AND i.isSold = false AND endDate > :today");
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
}
