package den.graduation.util;

import den.graduation.model.Voting;

import java.util.List;

public class VotingUtil {

    public static String createOrUpdateVoting(List<Voting> votingList) {
        String status;

        if (votingList != null && DataUtil.UpdateVoting(votingList) == true) {
            status = "Вы уже проголосовали, но возможно, получиться проголосовать еще - проверяем";
            if (DataUtil.isVoting(votingList) == false) {
                status = "Вы уже голосовали и проголосовать не сможете(время голосования до 11:00)";
            } else {
                status = "Вы уже проголосовали, но можете изменить свой голос";
            }
        } else if (DataUtil.timeEnd) {
            status = "Вы не можете проголосовать(время голосования до 11:00)";
        } else {
            status = "Вы можете проголосовать";
        }
        return status;
    }
}
