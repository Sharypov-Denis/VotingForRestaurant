package den.graduation.repository.datajpa;

import den.graduation.model.Restaurant;
import den.graduation.repository.RestaurantRepository;
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
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        //restaurant.setUser(crudUserRepository.getOne(userId));
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.getAll();
    }

    @Override
    public List<Restaurant> getAllRestaurants()
    {
        return crudRestaurantRepository.findAll();
    }

    @Transactional
    @Override
    public void updateById(int id) {
        crudRestaurantRepository.updateById(id);
    }

    @Transactional
    @Override
    public void updateByIdMinusOne(int id) {
        crudRestaurantRepository.updateByIdMinusOne(id);
    }

    @Override
    public Restaurant getOne(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

}
