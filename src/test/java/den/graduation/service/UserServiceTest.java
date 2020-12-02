package den.graduation.service;

import den.graduation.model.Role;
import den.graduation.model.User;
import den.graduation.repository.UserRepository;
import den.graduation.util.exception.NotFoundException;
import org.hibernate.annotations.Proxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static den.graduation.model.User.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})


public class UserServiceTest {

    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);

    public static User USER1 = new User(null, "testUser1", "testUser1@yandex.ru", "password", Role.USER);
    public static User USER2 = new User(null, "testUser2", "testUser2@yandex.ru", "password", Role.USER);
    public static User USER3;
    public static User USER4;


    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Before
    public void Start() {
        service.create(USER1);
        service.create(USER2);
        USER3 = service.getByEmail("testUser1@yandex.ru");
        USER4 = service.getByEmail("testUser2@yandex.ru");
    }

    @After
    public void Exit() {
        service.delete(USER3.getId());
        service.delete(USER4.getId());
    }


    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("testUser1@yandex.ru");
        assertEquals(user, USER3);

    }


/*
    @Test
    public void delete() throws Exception {
        service.delete(USER3.getId());
        assertNull(repository.get(100046));
    }

    @Test
    public void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

 */
}