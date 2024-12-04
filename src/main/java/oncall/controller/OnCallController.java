package oncall.controller;

import java.util.List;
import oncall.domain.DateEmployee;
import oncall.domain.MonthDay;
import oncall.domain.MonthlyDate;
import oncall.service.DateEmployeeService;
import oncall.service.InitializerService;
import oncall.utils.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {
    final InputView inputView;
    final OutputView outputView;

    final InitializerService initializerService;
    final DateEmployeeService dateEmployeeService;


    public OnCallController(InputView inputView, OutputView outputView, InitializerService initializerService,
                            DateEmployeeService dateEmployeeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.initializerService = initializerService;
        this.dateEmployeeService = dateEmployeeService;
    }

    public void run() {

        MonthDay monthAndDay = getMonthAndDayHandler();
        List<MonthlyDate> initMonth = initializerService.initMonth(monthAndDay.getMonth(), monthAndDay.getDay());

        // 중간에 잘못 입력하면 특정 구간으로 재이동
        List<String> weelkyEmployee = getWeeklyEmployeeHandler();
        List<String> holidayEmployee = null;
        while (weelkyEmployee == null || holidayEmployee == null) {
            holidayEmployee = getHolidayEmployeeHandler(weelkyEmployee);
            if (holidayEmployee == null) {
                weelkyEmployee = getWeeklyEmployeeHandler();
            }
        }

        List<DateEmployee> dateEmployees = dateEmployeeService.allocEmployee(initMonth, weelkyEmployee,
                holidayEmployee);
        outputView.printMonthlyDateEmployee(dateEmployees);
    }


    // Input list 반복
    public MonthDay getMonthAndDayHandler() {
        int month = 0;
        String day = null;
        while (month == 0 || day == null) {
            try {
                String input = inputView.getMonthAndDayofWeek();
                month = InputParser.parseMonth(input);
                day = InputParser.parseDayOfWeek(input);
                return new MonthDay(month, day);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // 일반 스트링 반복
    public List<String> getWeeklyEmployeeHandler() {
        List<String> input = null;
        while (input == null) {
            try {
                input = InputParser.parseWeeklyEmployee(inputView.getWeeklyEmployee());
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public List<String> getHolidayEmployeeHandler(List<String> weelky) {
        List<String> input = null;
        while (input == null) {
            try {
                input = InputParser.parseHolidayEmployee(weelky, inputView.getHolidayEmployee());
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return input;
    }
}
