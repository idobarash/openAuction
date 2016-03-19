package dao;

import dao.base.GenericDao;
import entity.ItemCategory;

import java.util.List;

public interface ItemCategoryDao extends GenericDao<ItemCategory> {

    List<ItemCategory> readAll();

    ItemCategory readByName(String categoryName);
}
