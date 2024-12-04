package oncall.service;

import java.util.LinkedList;
import java.util.List;
import oncall.domain.MonthlyDate;

public class InitializerService {

    public List<MonthlyDate> initMonth(int month, String date) {
        int days = 0;
        if (month == 2) {
            days = 28;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            days = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        }
        return initMonth(month, days, date);
    }

    public List<MonthlyDate> initMonth(int month, int days, String date) {
        List<MonthlyDate> monthlyDates = new LinkedList<>();
        String[] day = new String[]{"월", "화", "수", "목", "금", "토", "일"};
        int idx = findAtDaysArray(day, date);
        boolean isHoliday = false;
        for (int i = 1; i <= days; i++) {
            if (isHolidays(month, i)) {
                isHoliday = true;
            }
            MonthlyDate monthlyDate = new MonthlyDate(month, i, day[(findAtDaysArray(day, date) + i) % 7], isHoliday);
            monthlyDate.setWeekEnd();
            monthlyDates.add(monthlyDate);
        }

        return monthlyDates;
    }

    public int findAtDaysArray(String[] day, String date) {
        for (int i = 0; i < day.length; i++) {
            if (day[i].equals(date)) {
                return i;
            }
        }
        return 0;
    }

    public boolean isHolidays(int month, int day) {
        if (month == 1 && day == 1) {
            return true;
        }
        if (month == 3 && day == 1) {
            return true;
        }
        if (month == 5 && day == 5) {
            return true;
        }
        if (month == 6 && day == 6) {
            return true;
        }
        if (month == 8 && day == 15) {
            return true;
        }
        if (month == 10 && day == 3) {
            return true;
        }
        if (month == 10 && day == 9) {
            return true;
        }
        if (month == 12 && day == 25) {
            return true;
        }
        return false;
    }
}
