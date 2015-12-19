package dao.base;

import model.AbstractEntity;

/**
 * Created by ido on 18/12/2015.
 */
public interface GenericDao<T extends AbstractEntity> {

    void create(T entity);

    T read(Integer id, Class<T> entityType);

    T update(T entity);

    void delete(T entity);

}
