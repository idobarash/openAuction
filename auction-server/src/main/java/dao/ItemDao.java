package dao;

import dao.base.GenericDao;
import entity.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    List<Item> loadItemsByCategoryName(Integer categoryId, int firstResultIndex, Integer itemsPerPage);

    Long countAllItemsByCategory(Integer categoryId);
}
