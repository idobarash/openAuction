package dao.base;

import model.AbstractSoftDeletedEntity;

public class AbstractSoftDeletedJpaDao<T extends AbstractSoftDeletedEntity> extends AbstractJpaDao<T> {

    @Override
    public void delete(T entity) {
        entity.setIsActive(false);
        update(entity);
    }
}
