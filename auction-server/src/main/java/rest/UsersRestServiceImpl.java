package rest;


import dao.ItemDao;
import dto.ItemsWrapperListDto;
import entity.Item;
import entity.User;
import services.ItemsBusinessService;
import services.UsersBusinessService;

import javax.inject.Inject;
import java.util.List;

/**
 * User data REST service implementation.
 * Handles request-response operations for User data
 *
 * Author: Ido Barash
 */
public class UsersRestServiceImpl implements UsersRestService {

    @Inject
    private UsersBusinessService usersBusinessService;

    @Inject
    private ItemsBusinessService itemsBusinessService;

    @Override
    public User get(Integer id) {
        return usersBusinessService.getUserById(id);
    }

    @Override
    public Boolean saveNewUser(User user) {
        usersBusinessService.saveUser(user);
        return true;
    }

    @Override
    public User updateUser(User user) {
        return usersBusinessService.updateUser(user);
    }

    @Override
    public Boolean deleteUser(User user) {

        try {
            usersBusinessService.deleteUser(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public ItemsWrapperListDto getUserOngoingAuctions(Integer userId, int pageNumber, int pageSize) {

        ItemsWrapperListDto result = new ItemsWrapperListDto();

        List<Item> items = itemsBusinessService.getOngoingAuctionItems(userId, pageNumber, pageSize);
        result.setItemList(items);

        Long itemsCount = itemsBusinessService.countOnGoingAuctionsForUser(userId);
        result.setTotalItems(itemsCount);

        return result;
    }

    @Override
    public ItemsWrapperListDto getUserBiddedItems(Integer userId, int pageNumber, int pageSize) {

        ItemsWrapperListDto result = new ItemsWrapperListDto();

        List<Item> items = itemsBusinessService.getItemsBiddedByUser(userId, pageNumber, pageSize);
        result.setItemList(items);

        Long itemsCount = itemsBusinessService.countItemsBiddedByUser(userId);
        result.setTotalItems(itemsCount);

        return result;
    }

    @Override
    public ItemsWrapperListDto getUserFinishedAuctions(Integer userId, int pageNumber, int pageSize) {

        ItemsWrapperListDto result = new ItemsWrapperListDto();

        List<Item> items = itemsBusinessService.getFinishedAuctionItems(userId, pageNumber, pageSize);
        result.setItemList(items);

        Long itemsCount = itemsBusinessService.countFinishedAuctionsForUser(userId);
        result.setTotalItems(itemsCount);

        return result;
    }


}
