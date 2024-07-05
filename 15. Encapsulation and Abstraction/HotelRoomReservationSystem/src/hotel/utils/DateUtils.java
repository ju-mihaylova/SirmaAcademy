package hotel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    public static List<String> getDatesBetween(LocalDate startDate, LocalDate endDate, DateTimeFormatter formatter) {
        List<String> dates = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }

        return dates;
    }
}
