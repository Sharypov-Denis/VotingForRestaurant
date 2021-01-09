package den.graduation.repository.datajpa;


import den.graduation.service.UserService;
import den.graduation.model.Role;
import den.graduation.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/testPopulateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DataJPAUserRepositoryTest {

    public final static Integer ID_100010 = 100010;
    public final static User USER_100010 = new User(ID_100010, "userTest",
            "userTest@mail.ru", "userTest", Role.USER);

    public final static Integer ID_100020 = 100020;
    public final static User USER_100020 = new User(ID_100020, "newName",
            "new@mai.ru", "newPassword", Role.USER);

    public final static User USER_100030 = new User(100002, "adminNew",
            "adminnew@mail.ru", "password", Role.USER);
    public final static User USER_100040 = new User(100040, "userNew2",
            "userNew2@gmail.com", "password", Role.USER);

    @Autowired
    UserService userService;

    @Test
    public void create() {
        User user = userService.create(USER_100010);
        System.out.println(user);
        int id = user.id();
        System.out.println(id);
        System.out.println(userService.get(id));
        assertThat(user).isEqualTo(userService.get(id));
    }

    @Test
    public void getByEmail() {
        User user = userService.create(USER_100030);
        User user1 = userService.getByEmail("adminnew@mail.ru");
        System.out.print(user1);
        assertThat(user).isEqualToIgnoringGivenFields(USER_100030,"registered", "roles");
    }
/*
    @Test
    void delete() {
        User user = userService.create(USER_100030);
        userService.delete(100002);
        assertThrows(NotFoundException.class, ()-> userService.get(100002));
    }

 */
}

