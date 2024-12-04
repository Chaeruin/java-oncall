package oncall.service;

import java.util.LinkedList;
import java.util.List;
import oncall.domain.DateEmployee;
import oncall.domain.MonthlyDate;

public class DateEmployeeService {

    public List<DateEmployee> allocEmployee(List<MonthlyDate> monthlyDates, List<String> weekly, List<String> holiday) {
        List<DateEmployee> dateEmployees = new LinkedList<>();
        int idx_weelky = 0;
        int idx_holiday = 0;
        for (MonthlyDate monthlyDate : monthlyDates) {
            if (monthlyDate.getIsHoliday()) {
                idx_holiday = setDuplicateSequence(dateEmployees, monthlyDate, idx_holiday, holiday);
            } else if (!monthlyDate.getIsHoliday()) {
                idx_weelky = setDuplicateSequence(dateEmployees, monthlyDate, idx_weelky, weekly);
            }
        }
        return dateEmployees;
    }

    public int setDuplicateSequence(List<DateEmployee> dateEmployees, MonthlyDate monthlyDate, int idx,
                                    List<String> weekOrHoli) {
        int place = 0;
        String tempEmpl = null;
        if (dateEmployees.size() != 0 && dateEmployees.getLast().getNickName().equals(weekOrHoli.get(idx))) {
            place = idx + 1;
            tempEmpl = weekOrHoli.remove(place);
            dateEmployees.add(new DateEmployee(monthlyDate, tempEmpl));
        } else {
            dateEmployees.add(new DateEmployee(monthlyDate, weekOrHoli.get(idx++)));
        }
        if (idx == weekOrHoli.size()) {
            idx = 0;
            if (tempEmpl != null) {
                weekOrHoli.add(place, tempEmpl);
            }
        }
        return idx;
    }
}
