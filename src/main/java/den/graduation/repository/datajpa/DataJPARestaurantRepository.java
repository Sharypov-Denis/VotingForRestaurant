package den.graduation.repository.datajpa;

import den.graduation.model.Restaurant;
import den.graduation.repository.RestaurantRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJPARestaurantRepository implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJPARestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        if (!restaurant.isNew() && get(restaurant.getId(), userId) == null) {
            return null;
        }

        restaurant.setUser(crudUserRepository.getOne(userId));
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRestaurantRepository.delete(id, userId) != 0;
    }

    @Override
    public Restaurant get(int id, int userId) {
        return crudRestaurantRepository.findById(id)
                .filter(exercise -> exercise.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return crudRestaurantRepository.getAll(userId);
    }

    @Override
    public Restaurant getWithUser(int id, int userId) {
        return crudRestaurantRepository.getWithUser(id, userId);
    }

    @Override
    public List<Restaurant> getAllRestaurants()
    {
        return crudRestaurantRepository.getAllRestaurants();
    }

    public void updateById(int id) {
        crudRestaurantRepository.updateById(id);
    }

    @Override
    public Restaurant getOne(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

}
