package den.graduation.repository.datajpa;

import den.graduation.model.Menu;
import den.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT ex FROM Restaurant ex JOIN FETCH ex.user WHERE ex.id = ?1 and ex.user.id = ?2")
    Menu getWithRestaurant(int id, int restaurantId);

    @Query("SELECT m FROM Menu m")
    List<Menu> getAllMenu();

}
