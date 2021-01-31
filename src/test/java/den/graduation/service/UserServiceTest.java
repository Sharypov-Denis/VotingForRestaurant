package den.graduation.service;

import den.graduation.model.Role;
import den.graduation.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class UserServiceTest extends AbstractDataJpaTest{

    public static List<User> userInBase = new ArrayList<>();

    static {
        userInBase.add(new User(100000,"User", "user@yandex.ru", "password", Role.USER));
        userInBase.add(new User(100001,"Admin", "admin@gmail.com", "admin", Role.ADMIN));
        userInBase.add(new User(100002,"UserMax", "usermax@gmail.com", "12345", Role.USER));
        userInBase.add(new User(100003,"UserIgor", "userigor@gmail.com", "12345", Role.USER));
        userInBase.add(new User(100004,"UserPetr", "userpetr@gmail.com", "12345", Role.USER));
    }

    public final static Integer ID_100000 = 100000;
    public final static User USER_100000 = new User(null,"User", "user@yandex.ru", "password", Role.USER);
    public final static User USER_ID_100000 = new User(ID_100000,"User", "user@yandex.ru", "password", Role.USER);

    public final static Integer ID_100001 = 100001;
    public final static User USER_100001 = new User(null,"Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public final static User USER_ID_100001 = new User(null,"Admin", "admin@gmail.com", "admin", Role.ADMIN);

    public final static Integer ID_100002 = 100002;
    public final static User USER_100002 = new User(null,"UserMax", "usermax@gmail.com", "12345", Role.USER);
    public final static User USER_ID_100002 = new User(null,"UserMax", "usermax@gmail.com", "12345", Role.USER);

    public final static Integer ID_100003 = 100003;
    public final static User USER_100003 = new User(null,"UserIgor", "userigor@gmail.com", "12345", Role.USER);
    public final static User USER_ID_100003 = new User(null,"UserIgor", "userigor@gmail.com", "12345", Role.USER);

    public final static Integer ID_100004 = 100004;
    public final static User USER_100004 = new User(null,"UserPetr", "userpetr@gmail.com", "12345", Role.USER);
    public final static User USER_ID_100004 = new User(ID_100004,"UserPetr", "userpetr@gmail.com", "12345", Role.USER);
    public final static User USER_ID_100004_UPDATE = new User(ID_100004,"UserPetrNEW", "userpetrNEW@gmail.com", "12345", Role.USER);

    public final static Integer ID_100005 = 100005;
    public final static User USER_100005 = new User(null, "userTest",
            "userTest@mail.ru", "userTest", Role.USER);
    public final static User USER_ID_100005 = new User(100005, "userTest",
            "userTest@mail.ru", "userTest", Role.USER);


    @Autowired
    private UserService service;

    @After
    @Sql(scripts = "classpath:db/PopulateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
    public void updateBase() {
        System.out.println("вывод перед каждым");
    }

    @Test
    public void create() {
        service.create(USER_100005);
        assertEquals(service.get(100005),USER_ID_100005);
    }

    @Test
    public void createDuplicate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        service.delete(ID_100003);
        Assert.assertNull(service.get(ID_100003));
    }

    @Test
    public void get() {
        service.get(100000);
        Assert.assertNotNull(service.get(ID_100000));
    }

    @Test
    public void getNotFound() throws Exception {
        Assert.assertNull(service.get(100020));
        //assertThrows(NotFoundException.class, () -> service.get(100020));
    }

    @Test
    public void getByEmail() {
        assertEquals(service.getByEmail("user@yandex.ru"), USER_ID_100000);
    }

    @Test
    public void getAll() {
        List<User> getAllUser = service.getAll();
        assertEquals(getAllUser, userInBase);

    }

    @Test
    public void update() {
        service.update(USER_ID_100004_UPDATE);
        assertEquals(service.get(ID_100004), USER_ID_100004_UPDATE);
    }
/*
    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(100020));
    }

 */

    /*
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

    public void Start() {
        service.create(USER1);
        service.create(USER2);
        USER3 = service.getByEmail("testUser1@yandex.ru");
        USER4 = service.getByEmail("testUser2@yandex.ru");
    }


    public void Exit() {
        service.delete(USER3.getId());
        service.delete(USER4.getId());
    }



    public void getByEmail() throws Exception {
        User user = service.getByEmail("testUser1@yandex.ru");
        assertEquals(user, USER3);

    }


    public void delete() throws Exception {
        service.delete(USER3.getId());
        assertNull(repository.get(100046));
    }


    public void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }


    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }


    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }


    public void update() throws Exception {
        User updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }


    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}
     */
}