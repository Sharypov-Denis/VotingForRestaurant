package den.graduation.web;

import den.graduation.SecurityUtil;
import den.graduation.model.Restaurant;
import den.graduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class RestaurantRestController {

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        //log.info("get exercise {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        //log.info("delete exercise {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        //log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Restaurant create(Restaurant exercise) {
        int userId = SecurityUtil.authUserId();
        //checkNew(exercise);
       // log.info("create {} for user {}", exercise, userId);
        return service.create(exercise, userId);
    }

    public void update(Restaurant exercise, int id) {
        int userId = SecurityUtil.authUserId();
        //assureIdConsistent(exercise, id);
        //log.info("update {} for user {}", exercise, userId);
        service.update(exercise, userId);
    }
}