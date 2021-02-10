package den.graduation;

import den.graduation.model.Role;
import den.graduation.model.User;
import den.graduation.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockTestUser {

  //  @Mock
   // private UserService service;
//
   // private DataJPAUserRepository repository;
    private static final UserService serviceMock = mock(UserService.class);
    private static final User user = new User(null, "User", "user@yandex.ru", "password", Role.USER);
    private static final User user1 = new User(100000, "User", "user@yandex.ru", "password", Role.USER);

    @Test
    public void create() {
        when(serviceMock.getByEmail("user@yandex.ru")).thenReturn(new User(100000, "User", "user@yandex.ru", "password", Role.USER));
        Assert.assertEquals(serviceMock.getByEmail("user@yandex.ru"), new User(100000, "User", "user@yandex.ru", "password", Role.USER));
        assertEquals(serviceMock.getByEmail("user@yandex.ru"), user1);
        assertThat(serviceMock.getByEmail("user@yandex.ru")).isEqualToIgnoringGivenFields(user1,"id", "registered");

        when(serviceMock.getByEmail("user1@yandex.ru")).thenReturn(null);
        Assert.assertEquals(serviceMock.getByEmail("user1@yandex.ru"), null);
    }


}
