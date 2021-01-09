package den.graduation.repository;

import den.graduation.model.Restaurant;

import java.util.List;


public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    //default Restaurant getWithUser(int id, int userId) {
   //     throw new UnsupportedOperationException();
   // }

    List<Restaurant> getAllRestaurants();

    void updateById(int id);

    void updateByIdMinusOne(int id);

    Restaurant getOne(int id);
}
