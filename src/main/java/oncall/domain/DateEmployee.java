package oncall.domain;

public class DateEmployee {
    private final MonthlyDate monthlyDate;

    private final String nickName;

    public DateEmployee(MonthlyDate monthlyDate, String nickName) {
        this.monthlyDate = monthlyDate;
        this.nickName = nickName;
    }

    public MonthlyDate getMonthlyDate() {
        return monthlyDate;
    }

    public String getNickName() {
        return nickName;
    }
}
