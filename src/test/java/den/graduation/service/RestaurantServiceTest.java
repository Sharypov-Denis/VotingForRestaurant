package den.graduation.service;

import den.graduation.model.Restaurant;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceTest extends AbstractDataJpaTest {

    public static List<Restaurant> restaurantsInBase = new ArrayList<>();

    static {
        restaurantsInBase.add(new Restaurant(100001,"Русский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100002,"Индийский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100003,"Китайский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100004,"Европейский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100005,"Японский ресторан", 0));
    }

    public final static Integer ID_100001 = 100001;
    public final static Restaurant RESTAURANT_100001 = new Restaurant(null,"Русский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100001 = new Restaurant(ID_100001,"Русский ресторан", 0);

    public final static Integer ID_100002 = 100002;
    public final static Restaurant RESTAURANT_100002 = new Restaurant(null,"Индийский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100002 = new Restaurant(ID_100002,"Индийский ресторан", 0);

    public final static Integer ID_100003 = 100003;
    public final static Restaurant RESTAURANT_100003 = new Restaurant(null,"Китайский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100003 = new Restaurant(ID_100003,"Китайский ресторан", 0);

    public final static Integer ID_100004 = 100004;
    public final static Restaurant RESTAURANT_100004 = new Restaurant(null,"Европейский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100004 = new Restaurant(ID_100004,"Европейский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100004_UPDATE = new Restaurant(ID_100004,"Европейский ресторанUPDATE", 0);

    public final static Integer ID_100005 = 100005;
    public final static Restaurant RESTAURANT_100005 = new Restaurant(null,"Японский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100005 = new Restaurant(ID_100005,"Японский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100005_UPDATE = new Restaurant(ID_100005,"Японский ресторанUPDATE", 0);

    public final static Integer ID_100006 = 100006;
    public final static Restaurant RESTAURANT_100006 = new Restaurant(null,"ТестРесторанНью", 0);
    public final static Restaurant RESTAURANT_ID_100006 = new Restaurant(ID_100006,"ТестРесторанНью", 0);


    @Autowired
    private RestaurantService service;

    @After
    @Sql(scripts = "classpath:db/PopulateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
    public void updateBase() {
        System.out.println("вывод перед каждым");
    }

    @Test
    public void get() {
       // service.getOne(100001);
    }

    @Test
    public void delete() {
       // service.delete(ID_100002);
        //Assert.assertNull(service.get(ID_100002));
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
        //service.update(RESTAURANT_ID_100005_UPDATE);
       // assertEquals(service.get(ID_100005), RESTAURANT_ID_100005_UPDATE);
        //assertThat(RESTAURANT_ID_100005_UPDATE).isEqualToIgnoringGivenFields(service.get(ID_100005) "registered", "roles");
    }

    @Test
    public void create() {
       // service.create(RESTAURANT_100006);
       // assertEquals(service.get(100006),RESTAURANT_ID_100006);
    }

    @Test
    public void getAllRestaurants() {
    }

    @Test
    public void updateById() {
        //service.updateById(ID_100004);
        //assertEquals(service.get(ID_100004), RESTAURANT_ID_100004_UPDATE);
    }

    @Test
    public void updateByIdMinusOne() {
    }
}