package den.graduation.service;

import den.graduation.model.Restaurant;
import den.graduation.model.Voting;
import den.graduation.repository.datajpa.DataJPAVotingRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class VotingService {

    private final DataJPAVotingRepository repository;;

    public VotingService(DataJPAVotingRepository repository) {
        this.repository = repository;
    }

    public Voting create(Voting voting, int restaurantId, int userId) {
        Assert.notNull(voting, "restaurant must not be null");
        return repository.save(voting, restaurantId, userId);
    }
    public List<Voting> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }
}
