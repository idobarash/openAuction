package dao;

import dao.base.AbstractJpaDao;
import entity.Item;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named
public class ItemDaoJpaImpl extends AbstractJpaDao<Item> implements ItemDao {

    @Override
    public List<Item> loadItemsByCategoryName(Integer categoryId, int firstResultIndex, Integer itemsPerPage) {

        Query query;

        if (categoryId != null) {
            query = entityManager.createQuery("SELECT i FROM  Item AS i WHERE i.category.id = :categoryId ORDER BY i.creationDate DESC");
            query.setParameter("categoryId", categoryId);
        } else {
            query = entityManager.createQuery("SELECT i FROM  Item i ORDER BY i.creationDate DESC");
        }

        query.setFirstResult(firstResultIndex);
        query.setMaxResults(itemsPerPage);

        return query.getResultList();
    }

    @Override
    public Long countAllItemsByCategory(Integer categoryId) {

        Query query;

        if (categoryId != null) {
            query = entityManager.createQuery("SELECT COUNT(i) FROM Item AS i WHERE i.category.id = :categoryId");
            query.setParameter("categoryId", categoryId);
        } else {
            query = entityManager.createQuery("SELECT COUNT(i) FROM Item i");
        }

        return (Long) query.getSingleResult();
    }
}
