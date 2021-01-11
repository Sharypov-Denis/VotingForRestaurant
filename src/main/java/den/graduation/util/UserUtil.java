package den.graduation.util;


import den.graduation.model.Role;
import den.graduation.model.User;

public class UserUtil {

    public static User createNewFromTo(User userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

}