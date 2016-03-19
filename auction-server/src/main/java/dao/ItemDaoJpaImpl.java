package dao;

import dao.base.AbstractJpaDao;
import entity.Item;
import entity.ItemCategory;
import entity.User;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named
public class ItemDaoJpaImpl extends AbstractJpaDao<Item> implements ItemDao {

    @Override
    public List<ItemCategory> getItemsForVisitor(ItemCategory category) {

        Query query;

        if (category != null) {
            query = entityManager.createQuery("SELECT i FROM Item i WHERE i.category = category ORDER BY i.creationDate DESC");
        } else {
            query = entityManager.createQuery("SELECT i FROM Item i ORDER BY i.creationDate DESC");
        }

        query.setMaxResults(6);

        return query.getResultList();
    }
}
