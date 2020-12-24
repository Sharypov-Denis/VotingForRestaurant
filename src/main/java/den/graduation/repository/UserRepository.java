package den.graduation.repository;


import den.graduation.model.Restaurant;
import den.graduation.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    default User getWithRestaurant(int id) {
        throw new UnsupportedOperationException();
    }

}