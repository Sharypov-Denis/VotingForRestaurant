package den.graduation.web;

import den.graduation.SecurityUtil;
import den.graduation.model.Restaurant;
import den.graduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractRestaurantController {

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        //log.info("get restaurant {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        //log.info("delete restaurant {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        //log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        //checkNew(restaurant);
        // log.info("create {} for user {}", restaurant, userId);
        return service.create(restaurant, userId);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        //assureIdConsistent(restaurant, id);
        //log.info("update {} for user {}", restaurant, userId);
        service.update(restaurant, userId);
    }
}