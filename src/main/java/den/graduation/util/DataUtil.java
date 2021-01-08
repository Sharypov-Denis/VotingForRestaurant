package den.graduation.util;

import den.graduation.model.Voting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DataUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        return formatDateTime;

    }

    public static boolean isVoting(List<Voting> votingList) {
        boolean isVoting = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String str = format.format(new Date());
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        LocalDate localDateNow = convertToLocalDateViaInstant(date);
        LocalDateTime localDateTime = localDateNow.atTime(11, 00);

        for (int i = 0; i < votingList.size(); i++) {
            if (convertToLocalDateTimeViaInstant(votingList.get(i).getRegistered()).isAfter(localDateTime)) {
                isVoting = false;//"вы уже голосовали и проголосовать не сможете"
            }
        }
        return isVoting;
    }

    public static boolean UpdateVoting(List<Voting> votingList) {
        boolean isNew = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String str = format.format(new Date());
        Date date = null;

        try {
            date = format.parse(str);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < votingList.size(); i++) {
            if (convertToLocalDateTimeViaInstant(votingList.get(i).getRegistered()).isAfter(convertToLocalDateTimeViaInstant(date))) {
                isNew = true;//"вы уже проголосовали, но все равно можете проголосовать еще"
            }
        }
        return isNew;
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
