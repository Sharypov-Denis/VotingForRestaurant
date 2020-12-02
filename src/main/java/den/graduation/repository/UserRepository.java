package den.graduation.repository;


import den.graduation.model.Restaurant;
import den.graduation.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();


    default User getWithRestaurant(int id) {
        throw new UnsupportedOperationException();
    }

}