package den.graduation.repository;

import den.graduation.model.Menu;
import den.graduation.model.Restaurant;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id);

    Menu get(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);

    List<Menu> getAllMenu();

    default Menu getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }

}

