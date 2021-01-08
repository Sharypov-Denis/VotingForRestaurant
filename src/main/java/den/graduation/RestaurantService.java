package den.graduation;

import den.graduation.model.Restaurant;
import den.graduation.repository.RestaurantRepository;
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

    public Restaurant get(int id) {
        return repository.get(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
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
