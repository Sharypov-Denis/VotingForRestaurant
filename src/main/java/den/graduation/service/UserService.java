package den.graduation.service;

import den.graduation.AuthorizedUser;
import den.graduation.model.User;
import den.graduation.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;


@Service("userService")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
       repository.delete(id);
    }

    public User get(int id) {
        return repository.get(id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return repository.getByEmail(email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}