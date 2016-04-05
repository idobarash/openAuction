package dao;

import dao.base.AbstractJpaDao;
import entity.Bidding;
import entity.Item;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

/**
 * JPA DAO implementation to handle Bidding entities.
 *
 * Author: Ido Barash
 */
@Named
public class BiddingDaoJpaImpl extends AbstractJpaDao<Bidding> implements BiddingDao {

}
