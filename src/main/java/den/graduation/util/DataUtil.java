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

    public static boolean newVoting(List<Voting> votings) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String str = format.format(new Date());
        Date date = null;
        /*
        Date dateNow = new Date();
        LocalDate localDateNow = convertToLocalDateViaInstant(dateNow);
        System.out.println("Дата сегодня: " + dateNow);

        localDateNow.atTime(11,00);
        System.out.println("Дата сегодня + 11 часов: " + localDateNow);

         */

        try {
            date = format.parse(str);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Дата сегодня: " + date);
        LocalDate localDateNow = convertToLocalDateViaInstant(date);
        LocalDateTime localDateTime = localDateNow.atTime(11,00);
        System.out.println("Дата сегодня + 11 часов: " + localDateTime);

        boolean isNew = false;
        for (int i = 0; i < votings.size(); i++) {
            System.out.println("Дата голоса: " + votings.get(i).getRegistered());
            System.out.println("Дата сегодня: " + date);
            System.out.println("Дата голоса в LOCALDATE: " + convertToLocalDateViaInstant(votings.get(i).getRegistered()));
            if (convertToLocalDateTimeViaInstant(votings.get(i).getRegistered()).isAfter(localDateTime)){
                System.out.println("вы уже голосовали и проголосовать не сможете");
                isNew = true;
            }
        }
        return isNew;
    }

    public static boolean UpdateVoting(List<Voting> votings) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String str = format.format(new Date());
        Date date = null;
        /*
        Date dateNow = new Date();
        LocalDate localDateNow = convertToLocalDateViaInstant(dateNow);
        System.out.println("Дата сегодня: " + dateNow);

        localDateNow.atTime(11,00);
        System.out.println("Дата сегодня + 11 часов: " + localDateNow);

         */

        try {
            date = format.parse(str);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Дата сегодня: " + date);
        LocalDate localDateNow = convertToLocalDateViaInstant(date);
        LocalDateTime localDateTime = localDateNow.atTime(11,00);
        System.out.println("Дата сегодня + 11 часов: " + localDateTime);

        boolean isNew = false;
        for (int i = 0; i < votings.size(); i++) {
            System.out.println("Дата голоса: " + votings.get(i).getRegistered());
            System.out.println("Дата сегодня: " + date);
            System.out.println("Дата голоса в LOCALDATE: " + convertToLocalDateViaInstant(votings.get(i).getRegistered()));
            if (convertToLocalDateTimeViaInstant(votings.get(i).getRegistered()).isAfter(localDateTime)){
                System.out.println("вы уже голосовали и проголосовать не сможете");
                isNew = true;
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
