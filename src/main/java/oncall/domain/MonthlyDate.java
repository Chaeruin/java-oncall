package oncall.domain;

public class MonthlyDate {
    private final int month;
    private final int date;
    private final String day;

    private boolean isHoliday;


    public MonthlyDate(int month, int date, String day) {
        this.month = month;
        this.date = date;
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDate() {
        return this.date;
    }

    public String getDay() {
        return this.day;
    }

    public boolean getIsHoliday() {
        return this.isHoliday;
    }

    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }
}
