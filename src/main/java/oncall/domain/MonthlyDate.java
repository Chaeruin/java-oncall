package oncall.domain;

public class MonthlyDate {
    private final int month;
    private final int date;
    private final String day;

    private final boolean isHoliday;
    private boolean isWeekEnd;


    public MonthlyDate(int month, int date, String day, boolean isHoliday) {
        this.month = month;
        this.date = date;
        this.day = day;
        this.isHoliday = isHoliday;
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

    public void setWeekEnd() {
        this.isWeekEnd = false;
        if (this.day.equals("토") || this.day.equals("일")) {
            this.isWeekEnd = true;
        }
    }

    public boolean isHolidayAndWeekDay() {
        return this.isHoliday && !this.isWeekEnd;
    }
}
