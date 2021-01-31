package den.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@ApiModel(description = "Class representing a Restaurant in the application.")
@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant m WHERE m.id=:id"),
})

@Entity
@Table(name = "restaurant")
@Proxy(lazy = false)
public class Restaurant {

    public static final int START_SEQ = 100000;

    public static final String ALL_SORTED = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET_BETWEEN = "Restaurant.getBetween";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    // @NotBlank
    @Size(min = 2, max = 120)
    private String name;

    @Column(name = "numberOfVotes", nullable = false)

    private int numberOfVotes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    public List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Integer id) {
        this.id = id;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.name, restaurant.numberOfVotes);
    }

    public Restaurant(String name, int numberOfVotes) {
        this(null, name, numberOfVotes);
    }

    public Restaurant(Integer id, String name, int numberOfVotes) {
        this.id = id;
        this.name = name;
        this.numberOfVotes = numberOfVotes;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + //user +
                ", menu=" + menus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return numberOfVotes == that.numberOfVotes &&
                id.equals(that.id) &&
                name.equals(that.name) &&
                Objects.equals(menus, that.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfVotes, menus);
    }

    public boolean isNew() {
        return this.id == null;
    }

}
