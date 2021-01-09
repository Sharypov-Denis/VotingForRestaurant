package den.graduation.repository;

import den.graduation.model.Role;
import den.graduation.model.User;


public class UserRepositoryTest {
    public final static Integer ID_100010 = 100010;
    public final static User USER_100010 = new User(ID_100010, "userTest",
            "userTest@mail.ru", "userTest", Role.USER);

    public final static Integer ID_100020 = 100020;
    public final static User USER_100020 = new User(ID_100020, "newName",
            "new@mai.ru", "newPassword", Role.ADMIN);

    public final static User USER_ID_100030 = new User(100030, "adminNew",
            "adminNew@mail.ru", "password", Role.ADMIN);
    public final static User USER_ID_100040 = new User(100040, "userNew2",
            "userNew2@gmail.com", "password", Role.USER);

}
