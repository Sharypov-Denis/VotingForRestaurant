package den.graduation.repository.datajpa;

import den.graduation.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m JOIN FETCH Restaurant r WHERE r.id = ?1")
    Menu getWithRestaurant(int restaurantId);

    @Query("SELECT m FROM Menu m")
    List<Menu> getAllMenu();

}
