package dao;

import dao.base.GenericDao;
import entity.Item;
import entity.ItemCategory;

import java.util.List;

public interface ItemDao extends GenericDao<Item> {


    List<ItemCategory> getItemsForVisitor(ItemCategory category);
}
