package oncall;

import oncall.controller.OnCallController;
import oncall.service.DateEmployeeService;
import oncall.service.InitializerService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        InitializerService initializerService = new InitializerService();
        DateEmployeeService dateEmployeeService = new DateEmployeeService();

        OnCallController onCallController = new OnCallController(inputView, outputView, initializerService,
                dateEmployeeService);

        onCallController.run();
    }
}
