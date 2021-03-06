package den.graduation.util;

import den.graduation.SecurityUtil;
import den.graduation.model.Voting;
import den.graduation.service.RestaurantService;
import den.graduation.service.VotingService;
import den.graduation.web.mvc.JspRestaurantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class DataUtil {

    private static VotingService votingService;
    private static RestaurantService restaurantService;

    public DataUtil(VotingService votingService, RestaurantService restaurantService) {
        this.votingService = votingService;
        this.restaurantService = restaurantService;
    }

    private static final Logger log = LoggerFactory.getLogger(JspRestaurantController.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void createAndUpdateVoting(Integer id) {
        Voting voting = new Voting();
        List<Voting> votingList = votingService.getAllByUser(SecurityUtil.authUserId());
        LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        LocalDate localDateNowStart = LocalDate.now(ZoneId.of("Europe/Moscow"));
        LocalDateTime localDateTimeFinish = localDateNowStart.atTime(11, 00, 00);
        LocalDateTime localDateTimeStart = localDateNowStart.atTime(00, 00, 00);
        Integer idVote;
        Integer idRest;

        if (votingList.isEmpty()) {
            if (localDateTimeNow.isBefore(localDateTimeFinish)) {
                votingService.create(voting, id, SecurityUtil.authUserId());
                restaurantService.updateById(id);
                log.info("create vote for user {}", SecurityUtil.authUserId());
            }
        } else if (votingList != null) {
            for (int i = 0; i < votingList.size(); i++) {
                if (convertToLocalDateTimeViaInstant(votingList.get(i).getRegistered()).isAfter(localDateTimeStart)) {
                    idVote = votingList.get(i).getId();
                    idRest = votingList.get(i).getRestaurant().getId();
                    if (localDateTimeNow.isBefore(localDateTimeFinish)) {
                        votingService.delete(idVote);
                        restaurantService.updateByIdMinusOne(idRest);
                        votingService.create(voting, id, SecurityUtil.authUserId());
                        restaurantService.updateById(id);
                        log.info("update vote for user {}", SecurityUtil.authUserId());
                    }
                } else if (localDateTimeNow.isBefore(localDateTimeFinish)) {
                    votingService.create(new Voting(), id, SecurityUtil.authUserId());
                    restaurantService.updateById(id);
                    log.info("create vote for user {}", SecurityUtil.authUserId());
                }
            }
        }
    }

    public static String userVotesStatus(List<Voting> votingList) {
        String status = null;
        LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        LocalDate localDateNowStart = LocalDate.now(ZoneId.of("Europe/Moscow"));
        LocalDateTime localDateTimeFinish = localDateNowStart.atTime(11, 00, 00);
        LocalDateTime localDateTimeStart = localDateNowStart.atTime(00, 00, 00);

        if (votingList.isEmpty()) {
            if (localDateTimeNow.isBefore(localDateTimeFinish)) {
                status = "Вы можете проголосовать, т.к. вообще не голосовали никогда (время голосования до...)";
            } else {
                status = "Вы не можете проголосовать(время голосования до 11:00)";
            }
            return status;
        } else if (votingList != null) {
            for (int i = 0; i < votingList.size(); i++) {
                if (convertToLocalDateTimeViaInstant(votingList.get(i).getRegistered()).isAfter(localDateTimeStart)) {//если дата голоса после даты сегодня
                    if (localDateTimeNow.isBefore(localDateTimeFinish)) { // если время сейчас до максимального времени голоса
                        status = "Вы уже проголосовали, но можете проголосовать снова(изменить голос)";
                    } else {
                        status = "Вы уже голосовали сегодня и проголосовать не сможете(время голосования до ...)";
                    }
                } else if (localDateTimeNow.isBefore(localDateTimeFinish)) {
                    status = "Вы можете проголосовать, т.к. не голосовали сегодня (время голосования до...)";
                }
            }
        }
        return status;
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of("Europe/Moscow"))
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of("Europe/Moscow"))
                .toLocalDateTime();
    }

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public Date getToday() {
        return (new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))).getTime();
    }

    public static String getDateNow() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        return formatDateTime;

    }
}