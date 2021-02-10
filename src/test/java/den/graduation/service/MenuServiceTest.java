package den.graduation.service;

import den.graduation.model.Menu;
import den.graduation.model.Restaurant;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest extends AbstractDataJpaTest {

    public static List<Menu> menusInBaseForRestaurant100000 = Arrays.asList(
            new Menu(10001, new Restaurant(100000,"Русский ресторан", 0), "Картофельное пюре", 50, LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10002, new Restaurant(100000,"Русский ресторан", 0), "Куриная котлета", 80,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10003, new Restaurant(100000,"Русский ресторан", 0), "Компот", 20,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00))
    );
    public static List<Menu> menusInBase = Arrays.asList(
            new Menu(10001, new Restaurant(100000,"Русский ресторан", 0), "Картофельное пюре", 50, LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10002, new Restaurant(100000,"Русский ресторан", 0), "Куриная котлета", 80,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10003, new Restaurant(100000,"Русский ресторан", 0), "Компот", 20,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10004, new Restaurant(100001,"Индийский ресторан", 0), "Курица карри", 71,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10005, new Restaurant(100001,"Индийский ресторан", 0), "Пропаренный рис", 39,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10006, new Restaurant(100001,"Индийский ресторан", 0), "Масала", 27,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10007, new Restaurant(100002,"Китайский ресторан", 0), "Утка по пекински", 120,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10008, new Restaurant(100002,"Китайский ресторан", 0), "Жареный ананас", 101,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10009, new Restaurant(100002,"Китайский ресторан", 0), "Китайский чай", 43,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10010, new Restaurant(100003,"Европейский ресторан", 0), "Стейк Канзас", 270,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10011, new Restaurant(100003,"Европейский ресторан", 0), "Картофель фри", 56,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10012, new Restaurant(100003,"Европейский ресторан", 0), "Безалкогольное пиво", 39,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10013, new Restaurant(100004,"Японский ресторан", 0), "Филадельфия", 260,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10014, new Restaurant(100004,"Японский ресторан", 0), "Вок из говядины", 200,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00)),
            new Menu(10015, new Restaurant(100004,"Японский ресторан", 0), "Японский чай", 50,LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00))

    );


    //private static final DataJPAMenuRepository dataJpaMenuMock = mock(DataJPAMenuRepository.class);
    @Autowired
    private MenuService service;
    private static final Menu menu = new Menu(null, "Картофельное пюре", 50);
    private static final Menu menu_ID = new Menu(10001, "Картофельное пюре", 50);

    private static final Menu menu_10003 = new Menu(10003, "Компот", 20);
    private static final Integer id_for_menu_10003 = 100000;
    private static final Menu menu_10003_update = new Menu(10003, "КомпотUPDATE", 51);

    private static final Restaurant restaurant_100000 = new Restaurant(100000, "Русский ресторан0", 0);

    private static final Menu newMenu = new Menu(null,"Японский чайNew", 50);// 100005, 100000

    private static final LocalDateTime localDateTime = LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00);



    @Test
    public void get() {
        assertThat(service.get(10001, 100000)).isEqualToIgnoringGivenFields(menu_ID, "restaurant", "dateTime");
        Assert.assertNull(service.get(100016, 100000));
        //assertEquals(service.get(10001, 100000),menu_ID);
       // when(dataJpaMenuMock.get(10001, 100000)).thenReturn(menu);
      //  assertEquals(dataJpaMenuMock.get(10001, 100000), menuService.get(10001, 100000));
        //verify(serviceMock, timeout(100)).get(10001, 100000);
      //  assertEquals(serviceMock.get(10001, 100000), menu);
       // Assert.assertNotNull(serviceMock.get(10001, 100000));
      //  assertThat(serviceMock.get(10001, 100000)).isEqualToIgnoringGivenFields(menu, "id", "dateTime", "restaurant");
      //  assertNull(serviceMock.get(10000, 100000));
        // проверка вызова метода в течение 10 мс
       // verify(serviceMock, times(4)).get(10001, 100000);

    }

    @Test
    public void delete() {
        service.delete(10002);
        Assert.assertNull(service.get(10002, 100000));
    }

    @Test
    public void getAll() {
        assertThat(menusInBaseForRestaurant100000).usingElementComparatorIgnoringFields("restaurant", "dateTime").isEqualTo(service.getAll(100000));
        //assertArrayEquals("Wrong array", service.getAll(100000).toArray(), menusInBase.toArray());
    }

    @Test
    public void update() {
        menu_10003_update.setRestaurant(restaurant_100000);
        menu_10003_update.setDateTime(LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00));
        service.update(menu_10003_update, 100000);
        assertThat(service.get(10003, 100000)).isEqualToIgnoringGivenFields(menu_10003_update,"restaurant", "dateTime");
    }

    @Test
    public void create() {
        newMenu.setRestaurant(restaurant_100000);
        newMenu.setDateTime(LocalDateTime.of(2021,Month.JANUARY,12, 00,00,00));
        service.create(newMenu, 100000);
        assertThat(service.get(100005, 100000)).isEqualToIgnoringGivenFields(newMenu,"restaurant", "dateTime");

        //assertEquals(service.get(100005, 100000),newMenu);
    }

    @Test
    public void getAllMenu() {
        assertThat(menusInBase).usingElementComparatorIgnoringFields("restaurant", "dateTime").isEqualTo(service.getAllMenu());
        //assertArrayEquals("Wrong array", service.getAllMenu().toArray(), menusInBase.toArray());
    }
}