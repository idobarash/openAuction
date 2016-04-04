package dao.base;

import entity.AbstractSoftDeletedEntity;

/**
 * Basic implementation of the Basic DAO with simple
 * CRUD.
 *
 * Notice: Delete method performs a soft delete --> set active field to false and update.
 *
 * @param <T> entity
 *
 * Author: Ido Barash
 */
public class AbstractSoftDeletedJpaDao<T extends AbstractSoftDeletedEntity> extends AbstractJpaDao<T> {

    @Override
    public void delete(T entity) {

        // Set active field to false
        entity.setIsActive(false);
        update(entity);
    }
}
