package den.graduation.util;

import den.graduation.model.Voting;
import den.graduation.service.RestaurantService;
import den.graduation.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VotingUtil {

    @Autowired
    private static VotingService votingService;

    @Autowired
    private RestaurantService restaurantService;

    public static String createOrUpdateVoting(List<Voting> votingList) {
        Voting voting = new Voting();
        String status;

        if (votingList != null && DataUtil.UpdateVoting(votingList) == true) {
            status = "Вы уже проголосовали, но возможно, получиться проголосовать еще - проверяем";
            if (DataUtil.isVoting(votingList) == false) {
                status = "Вы уже голосовали и проголосовать не сможете";
            } else {
                status = "Вы уже проголосовали, но можете проголосовать снова";
            }
        } else {
            status = "Вы можете проголосовать";
        }
        return status;
    }
}
