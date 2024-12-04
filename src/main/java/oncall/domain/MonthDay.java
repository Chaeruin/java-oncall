package oncall.domain;

public class MonthDay {
    private final int month;
    private final String day;

    public MonthDay(int month, String day) {
        this.day = day;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }
}
