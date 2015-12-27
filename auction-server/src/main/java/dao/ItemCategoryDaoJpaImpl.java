package dao;

import dao.base.AbstractJpaDao;
import entity.ItemCategory;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named
public class ItemCategoryDaoJpaImpl extends AbstractJpaDao<ItemCategory> implements ItemCategoryDao {

    @Override
    public List<ItemCategory> readAll() {
        Query query = entityManager.createQuery("SELECT ic FROM ItemCategory  ic");
        return query.getResultList();
    }
}
