package den.graduation.repository.datajpa;

import den.graduation.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudVotingRepository extends JpaRepository<Voting, Integer> {

    @Modifying
    @Query("DELETE FROM Voting v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Voting v WHERE v.user.id = :userId")
    List<Voting> getAllByUser(@Param("userId") int userId);

}
