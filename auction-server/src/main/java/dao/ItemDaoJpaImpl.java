package dao;

import dao.base.AbstractJpaDao;
import entity.Item;
import entity.ItemCategory;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named
public class ItemDaoJpaImpl extends AbstractJpaDao<Item> implements ItemDao {

    @Override
    public List<Item> loadItemsByCategoryName(String categoryName, int firstResultIndex, Integer itemsPerPage) {

        Query query;

        if (categoryName != null) {
            query = entityManager.createQuery("SELECT i FROM Item i LEFT JOIN FETCH ItemCategory c WHERE c.name = :categoryName ORDER BY i.creationDate DESC");
            query.setParameter("categoryName", categoryName);
        } else {
            query = entityManager.createQuery("SELECT i FROM Item i ORDER BY i.creationDate DESC");
        }

        query.setFirstResult(firstResultIndex);
        query.setMaxResults(itemsPerPage);

        return query.getResultList();
    }

    @Override
    public Long countAllItemsByCategory(String categoryName) {

        Query query;

        if (categoryName != null) {
            query = entityManager.createQuery("SELECT COUNT(i) FROM Item i LEFT JOIN FETCH ItemCategory c WHERE c.name = :categoryName");
            query.setParameter("categoryName", categoryName);
        } else {
            query = entityManager.createQuery("SELECT COUNT(i) FROM Item i");
        }

        return (Long) query.getSingleResult();
    }
}
