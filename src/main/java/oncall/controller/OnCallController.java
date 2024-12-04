package oncall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.utils.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {
    final InputView inputView;
    final OutputView outputView;


    public OnCallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        // 중간에 잘못 입력하면 특정 구간으로 재이동
        List<String> weelkyEmployee = getWeeklyEmployeeHandler();
        List<String> holidayEmployee = null;
        while (weelkyEmployee == null || holidayEmployee == null) {
            holidayEmployee = getHolidayEmployeeHandler(weelkyEmployee);
            if (holidayEmployee == null) {
                weelkyEmployee = getWeeklyEmployeeHandler();
            }
        }

    }


    // Input list 반복
    public Map<Integer, String> getMonthAndDayHandler() {
        int month = 0;
        String day = null;
        Map<Integer, String> monthAndDays = new HashMap<>();
        while (month == 0 || day == null) {
            try {
                String input = inputView.getMonthAndDayofWeek();
                month = InputParser.parseMonth(input);
                day = InputParser.parseDayOfWeek(input);
                monthAndDays.put(month, day);
                return monthAndDays;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return monthAndDays;
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
