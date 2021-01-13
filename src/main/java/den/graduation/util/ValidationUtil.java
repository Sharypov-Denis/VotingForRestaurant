package den.graduation.util;

import den.graduation.model.Menu;
import den.graduation.model.Restaurant;
import den.graduation.model.User;
import den.graduation.util.exception.IllegalRequestDataException;
import den.graduation.util.exception.NotFoundException;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {
    private static final Validator validator;

    static {
        //  From Javadoc: implementations are thread-safe and instances are typically cached and reused.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //  From Javadoc: implementations of this interface must be thread-safe
        validator = factory.getValidator();
    }

    private ValidationUtil() {
    }

    public static <T> void validate(T bean) {
        // https://alexkosarev.name/2018/07/30/bean-validation-api/
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static void checkNewUser(User user) {
        if (!user.isNew()) {
            throw new IllegalRequestDataException(user + " must be new (id=null)");
        }
    }

    public static void checkNewRestaurant(Restaurant restaurant) {
        if (!restaurant.isNew()) {
            throw new IllegalRequestDataException(restaurant + " must be new (id=null)");
        }
    }

    public static void checkNewMenu(Menu menu) {
        if (!menu.isNew()) {
            throw new IllegalRequestDataException(menu + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(User user, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (user.isNew()) {
            user.setId(id);
        } else if (user.id() != id) {
            throw new IllegalRequestDataException(user + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }
}