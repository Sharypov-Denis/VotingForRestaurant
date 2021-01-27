package den.graduation.repository.datajpa;

import den.graduation.model.Menu;
import den.graduation.repository.MenuRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJPAMenuRepository implements MenuRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudMenuRepository crudMenuRepository;

    public DataJPAMenuRepository(CrudRestaurantRepository crudRestaurantRepository, CrudMenuRepository crudMenuRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
       /* if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }

        */
        //menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);

    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return crudMenuRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }

    public Menu getWithRestaurant(int restaurantId) {
        return crudMenuRepository.getWithRestaurant(restaurantId);
    }

    public List<Menu> getAllMenu() {
        return crudMenuRepository.getAllMenu();
    }


}
