package den.graduation.repository.datajpa;

import den.graduation.model.Restaurant;
import den.graduation.model.User;
import den.graduation.model.Voting;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJPAVotingRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudMenuRepository crudMenuRepository;
    private final CrudVotingRepository crudVotingRepository;

    public DataJPAVotingRepository(CrudRestaurantRepository crudRestaurantRepository, CrudMenuRepository crudMenuRepository, CrudVotingRepository crudVotingRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMenuRepository = crudMenuRepository;
        this.crudVotingRepository = crudVotingRepository;
    }

    @Transactional
    public Voting save(Voting voting, int restaurantId, int userId) {
        voting.setUser(new User(userId));
        voting.setRestaurant(new Restaurant(restaurantId));
        System.out.println("тест голоса: " + voting);
        return crudVotingRepository.save(voting);
    }

    public List<Voting> getAllByUser(int userId) {
        return crudVotingRepository.getAllByUser(userId);
    }

    public void delete(int id) {
        crudVotingRepository.delete(id);
    }

}
