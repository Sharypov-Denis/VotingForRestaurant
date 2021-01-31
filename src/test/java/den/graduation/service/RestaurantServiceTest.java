package den.graduation.service;

import den.graduation.model.Restaurant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantServiceTest extends AbstractDataJpaTest {

    public static List<Restaurant> restaurantsInBase = new ArrayList<>();

    static {
        restaurantsInBase.add(new Restaurant(100000,"Русский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100001,"Индийский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100002,"Китайский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100003,"Европейский ресторан", 0));
        restaurantsInBase.add(new Restaurant(100004,"Японский ресторан", 0));
    }

    public final static Integer ID_100000 = 100000;
    public final static Restaurant RESTAURANT_100000 = new Restaurant(null,"Русский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100000 = new Restaurant(ID_100000,"Русский ресторан", 0);

    public final static Integer ID_100001 = 100001;
    public final static Restaurant RESTAURANT_100001 = new Restaurant(null,"Индийский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100001 = new Restaurant(ID_100001,"Индийский ресторан", 0);

    public final static Integer ID_100002 = 100002;
    public final static Restaurant RESTAURANT_100002 = new Restaurant(null,"Китайский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100002 = new Restaurant(ID_100002,"Китайский ресторан", 0);

    public final static Integer ID_100003 = 100003;
    public final static Restaurant RESTAURANT_100003 = new Restaurant(null,"Европейский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100003 = new Restaurant(ID_100003,"Европейский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100003_UPDATE = new Restaurant(ID_100003,"Европейский ресторанUPDATE", 0);

    public final static Integer ID_100004 = 100004;
    public final static Restaurant RESTAURANT_100004 = new Restaurant(null,"Японский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100004 = new Restaurant(ID_100004,"Японский ресторан", 0);
    public final static Restaurant RESTAURANT_ID_100004_UPDATE = new Restaurant(ID_100004,"Японский ресторанUPDATE", 0);

    public final static Integer ID_100005 = 100005;
    public final static Restaurant RESTAURANT_100005 = new Restaurant(null,"ТестРесторан", 0);
    public final static Restaurant RESTAURANT_ID_100005 = new Restaurant(ID_100005,"ТестРесторан", 0);


    @Autowired
    private RestaurantService service;

    @After
    @Sql(scripts = "classpath:db/PopulateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
    public void updateBase() {
    }

    @Test
    public void get() {
        assertThat(service.getOne(100001)).isEqualToIgnoringGivenFields(RESTAURANT_ID_100001, "menus");

    }
    @Test
    public void delete() {
        service.delete(ID_100002);
        Assert.assertNull(service.get(ID_100002));
    }

    @Test
    public void update() {
        service.update(RESTAURANT_ID_100004_UPDATE);
        assertThat(service.get(ID_100004)).isEqualToIgnoringGivenFields(RESTAURANT_ID_100004_UPDATE,"menus");
    }

    @Test
    public void updateById() {
        service.updateById(ID_100003);
        RESTAURANT_ID_100003.setNumberOfVotes(+1);
        assertThat(service.get(ID_100003)).isEqualToIgnoringGivenFields(RESTAURANT_ID_100003,"menus");
    }

    @Test
    public void create() {
        service.create(RESTAURANT_100005);
        assertThat(service.get(ID_100005)).isEqualToIgnoringGivenFields(RESTAURANT_ID_100005,"menus");
    }

    @Test
    public void getAllRestaurants() {
        assertThat(restaurantsInBase).usingElementComparatorIgnoringFields("menus").isEqualTo(service.getAll());
    }

    @Test
    public void updateByIdMinusOne() {
        service.updateByIdMinusOne(ID_100002);
        RESTAURANT_ID_100002.setNumberOfVotes(-1);
        assertThat(service.get(ID_100002)).isEqualToIgnoringGivenFields(RESTAURANT_ID_100002,"menus");
    }
}