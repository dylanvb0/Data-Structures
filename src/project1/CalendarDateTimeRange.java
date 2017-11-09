package project1;

public class CalendarDateTimeRange extends CalendarDateRange {
	int hours;
	int minutes;
	int seconds;

	public CalendarDateTimeRange(int years, int days, int hours, int minutes, int seconds) {
		super(years, days);
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public CalendarDateTimeRange() {
		super();
	}
}
