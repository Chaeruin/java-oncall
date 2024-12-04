package oncall.enums;

public enum ErrorMessage {
    INVALID_INPUT("[ERROR] 유효하지 않은 입력입니다."),
    INVALID_LENGTH("[ERROR] 닉네임은 5자를 초과할 수 없습니다."),
    INVALID_NUMBER_MONTH("[ERROR] 근무 월은 1월부터 12월 까지입니다."),
    INVALID_EMPL("[ERROR] 근무자 수가 유효하지 않습니다."),
    INVALID_NOT_EXISTED_DAY("[ERROR] 근무 요일은 월 ~ 일 만 가능합니다.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
