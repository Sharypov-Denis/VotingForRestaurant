package den.graduation.util;

import den.graduation.model.Voting;

import java.util.List;

public class VotingUtil {

    public static String createOrUpdateVoting(List<Voting> votingList) {
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
