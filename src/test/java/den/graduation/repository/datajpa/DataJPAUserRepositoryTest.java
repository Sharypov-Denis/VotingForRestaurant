package den.graduation.repository.datajpa;


import den.graduation.UserService;
import den.graduation.model.Role;
import den.graduation.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/testPopulateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DataJPAUserRepositoryTest{

    public static final User USER1 = new User(100011, "User1", "user11@yandex.ru", "password", Role.USER);
    public static final User USER2 = new User(100012, "User2", "admin11@gmail.com", "admin", Role.USER);
    public static final User USER = new User( null, "User2", "admin111@gmail.com", "admin", Role.USER);

    @Autowired
    UserService userService;

    @Test
    public void create() {
        userService.create(USER);
        System.out.println(userService.getByEmail("admin111@gmail.com"));
        assertEquals(userService.getByEmail("admin111@gmail.com"), USER);
       // assertThrows(NotFoundException.class, () -> userService.get(UserRepositoryTest.ID_100010));
    }
}
