package dao.base;

import entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Basic implementation of the Basic DAO with simple
 * CRUD.
 *
 * Notice: Delete method performs true delete.
 *
 * @param <T> entity
 */
public class AbstractJpaDao<T extends AbstractEntity> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T read(Integer id, Class<T> entityType) {
        return entityManager.find(entityType, id);
    }

    @Override
    public T update(T entity) {
        T val = entityManager.merge(entity);
        return val;
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

}
