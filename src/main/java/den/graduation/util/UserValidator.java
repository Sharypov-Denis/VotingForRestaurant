package den.graduation.util;

import den.graduation.model.Restaurant;
import den.graduation.model.User;
import den.graduation.repository.RestaurantRepository;
import den.graduation.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

@Component
public class UserValidator implements org.springframework.validation.Validator {

    private final UserRepository repository;
    private final RestaurantRepository restaurantRepository;

    public UserValidator(UserRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    public void validate1(Object target, Errors errors) {
        Restaurant restaurant = (Restaurant) target;
        String name = restaurant.getName();

        List<Restaurant> res = restaurantRepository.getAllRestaurants();
        for (int i = 0; i < res.size(); i++) {
            if ((res.get(i).getName()).equals(name)) {
                errors.rejectValue(
                        "name", "","This name restaurant is already in use"
                );
            }
        }
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "This email is already in use");
        String email = user.getEmail();
        if (repository.getByEmail(email) != null) {
            errors.rejectValue(
                    "email", "","This email is already in use"
            );
        }

    }
}
