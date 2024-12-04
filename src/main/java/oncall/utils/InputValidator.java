package oncall.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.enums.ErrorMessage;

public class InputValidator {

    public static boolean isMonthOK(String input) {
        try {
            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 12) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_MONTH.getErrorMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
        return true;
    }

    public static boolean isDayOK(String input) {
        String[] days = new String[]{"월", "화", "수", "목", "금", "토", "일"};
        for (String day : days) {
            if (input.equals(day)) {
                return true;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_NOT_EXISTED_DAY.getErrorMessage());
    }

    public static boolean isNotNone(String input) {
        String[] inputs = input.split(",", -1);
        for (String inp : inputs) {
            if (inp.equals("") || inp.equals("  ") || inp.equals("   ") || inp.equals("    ") || inp.equals("     ")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
        return true;
    }

    public static boolean isOverFiveNickNameLength(String input) {
        String[] inputs = input.split(",", -1);
        for (String inp : inputs) {
            if (inp.length() > 5) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_LENGTH.getErrorMessage());
            }
        }
        return true;
    }

    public static boolean isFiveTo35People(String input) {
        String[] inputs = input.split(",", -1);
        if (inputs.length < 5 || inputs.length > 35) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LENGTH.getErrorMessage());
        }
        return true;
    }

    public static boolean isNotDuplicate(String input) {
        String[] inputs = input.split(",", -1);
        for (int i = 0; i < inputs.length; i++) {
            for (int j = i + 1; j < inputs.length; j++) {
                compareTo(inputs[i], inputs[j]);
            }
        }
        return true;
    }

    public static void compareTo(String one, String two) {
        if (one.equals(two)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    public static boolean isEmployeeCondition(String input) {
        if (isNotNone(input) && isOverFiveNickNameLength(input) && isFiveTo35People(input) && isNotDuplicate(input)
                && isWeelkyOrHolidayEmployeeOnce(input)) {
            return true;
        }
        return false;
    }

    public static boolean isWeelkyHolidaySameEmployee(List<String> weekly, String holiday) {
        Set<String> weelkySet = new HashSet<String>(weekly);
        Set<String> holidaySet = new HashSet<String>(Arrays.asList(holiday.split(",", -1)));

        // 여기 체킹
        if (!weelkySet.containsAll(holidaySet) || !holidaySet.containsAll(weelkySet)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMPL.getErrorMessage());
        }
        return true;
    }

    public static boolean isWeelkyOrHolidayEmployeeOnce(String weekly) {
        Set<String> weelkySet = new HashSet<String>(Arrays.asList(weekly.split(",", -1)));
        // 여기 체킹
        if (weelkySet.size() != Arrays.asList(weekly.split(",", -1)).size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMPL.getErrorMessage());
        }
        return true;
    }


}
