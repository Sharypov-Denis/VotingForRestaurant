package den.graduation.repository;

import den.graduation.model.Restaurant;

import java.util.List;


public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int id, int userId);

    Restaurant get(int id, int userId);

    List<Restaurant> getAll(int userId);

    default Restaurant getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    List<Restaurant> getAllRestaurants();

    void updateById(int id);

    Restaurant getOne(int id);
}
