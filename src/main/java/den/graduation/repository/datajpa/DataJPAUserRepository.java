package den.graduation.repository.datajpa;

import den.graduation.model.Role;
import den.graduation.model.User;
import den.graduation.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJPAUserRepository implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    public DataJPAUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        User newUser = new User(null, user.getName(), user.getEmail().toLowerCase(), user.getPassword(), Role.USER);
        return crudUserRepository.save(newUser);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll();
    }

    @Override
    public User getWithRestaurant(int id) {
        return crudUserRepository.getWithRestaurant(id);
    }
}
