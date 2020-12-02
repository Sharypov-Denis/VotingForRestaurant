package den.graduation.repository;
import den.graduation.model.Restaurant;

import java.util.List;


public interface RestaurantRepository {
    // null if updated meal do not belong to userId
    Restaurant save(Restaurant restaurant, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Restaurant get(int id, int userId);

    // ORDERED dateTime desc
    List<Restaurant> getAll(int userId);

    default Restaurant getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    List<Restaurant> getAllRestaurants();

    void updateById(int id);

    Restaurant getOne(int id);
}
