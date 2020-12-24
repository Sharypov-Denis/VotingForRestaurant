package den.graduation.service;

import den.graduation.model.Menu;
import den.graduation.model.Restaurant;
import den.graduation.repository.MenuRepository;
import den.graduation.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu get(int id, int restaurantId) {
        return repository.get(id, restaurantId);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public List<Menu> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        repository.save(menu, restaurantId);
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    public List<Menu> getAllMenu() {
        return repository.getAllMenu();
    }
}
