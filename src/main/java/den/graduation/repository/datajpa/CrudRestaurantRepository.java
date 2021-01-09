package den.graduation.repository.datajpa;

import den.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant ex WHERE ex.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();

    @Modifying
    @Transactional
    @Query("UPDATE Restaurant r SET r.numberOfVotes = r.numberOfVotes + 1 WHERE r.id =:id")
    int updateById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Restaurant r SET r.numberOfVotes = r.numberOfVotes - 1 WHERE r.id =:id")
    int updateByIdMinusOne(@Param("id") int id);

    @Query("SELECT ex FROM Restaurant ex WHERE ex.id=:id")
    Restaurant getOne(int id);

    @Query("SELECT ex FROM Restaurant ex WHERE ex.id=:id")
    Restaurant get(int id);
}