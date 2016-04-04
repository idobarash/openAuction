package dao.base;

import entity.AbstractEntity;

/**
 * Basic Data Access Object
 *
 * Declares basic CRUD (Create, Read, Update, Delete)
 *
 * @param <T> entity
 *
 * Author: Ido Barash
 */
public interface GenericDao<T extends AbstractEntity> {

    /**
     * Persist entity
     *
     * @param entity the entity to create
     */
    void create(T entity);

    /**
     * Read entity by primary key
     *
     * @param id the primary key
     * @param entityType the entity class type
     * @return An entity if found
     */
    T read(Integer id, Class<T> entityType);

    /**
     * Update a persisted entity in the DB.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Delete an entity from the DB
     *
     * @param entity the entity to delete
     */
    void delete(T entity);

}
