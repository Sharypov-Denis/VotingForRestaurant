package den.graduation.service;

import den.graduation.model.Restaurant;
import den.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id, int userId) {
        return repository.get(id, userId);
    }

    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    public List<Restaurant> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Restaurant exercise, int userId) {
        Assert.notNull(exercise, "exercise must not be null");
        repository.save(exercise, userId);
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant, userId);
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.getAllRestaurants();
    }

    @Transactional
    public void updateById(int id) {
        repository.updateById(id);
    }


    public Restaurant getOne(int id) {
        return repository.getOne(id);
    }

}
