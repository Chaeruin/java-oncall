package oncall.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InputParser {

    public static int parseMonth(String input) {
        String[] inputs = input.split(",", -1);
        if (InputValidator.isMonthOK(inputs[0])) {
            return Integer.parseInt(inputs[0]);
        }
        return 0;
    }

    public static String parseDayOfWeek(String input) {
        String[] inputs = input.split(",", -1);
        if (InputValidator.isDayOK(inputs[1])) {
            return inputs[1];
        }
        return null;
    }

    public static List<String> parseWeeklyEmployee(String input) {
        if (InputValidator.isEmployeeCondition(input)) {
            LinkedList<String> returnList = (LinkedList<String>) Arrays.asList(input.split(",", -1));
            return returnList;
        }
        return null;
    }

    public static List<String> parseHolidayEmployee(List<String> weekly, String holiday) {
        if (InputValidator.isEmployeeCondition(holiday) && InputValidator.isWeelkyHolidaySameEmployee(weekly,
                holiday)) {
            LinkedList<String> returnList = (LinkedList<String>) Arrays.asList(holiday.split(",", -1));
            return returnList;
        }
        return null;
    }
}
