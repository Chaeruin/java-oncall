package oncall.view;

import java.util.List;
import oncall.domain.DateEmployee;

public class OutputView {

    public void printMonthlyDateEmployee(List<DateEmployee> employees) {
        for (DateEmployee employee : employees) {
            System.out.print(employee.getMonthlyDate().getMonth() + "월 ");
            System.out.print(employee.getMonthlyDate().getDate() + "일 ");
            System.out.print(employee.getMonthlyDate().getDay());
            if (employee.getMonthlyDate().isHolidayAndWeekDay()) {
                System.out.print("(휴일)");
            }
            System.out.println(" " + employee.getNickName());
        }
    }
}
