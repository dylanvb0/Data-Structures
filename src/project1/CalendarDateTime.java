package project1;

public class CalendarDateTime extends CalendarDate {
	int hour;
	int minute;
	int second;
	
	private static final int SECONDS_IN_DAY = 86400;

	// date time object constructor with params
	public CalendarDateTime(int year, int month, int day, int hour, int minute, int second)
			throws IllegalDate {
		super(year, month, day);
		if(hour > 23 || hour < 0){ // validate hours
			throw new IllegalDate();
		}else if(minute > 59 || minute < 0){ // validate minutes
			throw new IllegalDate();
		}else if(second > 59 || second < 0){ // validate seconds
			throw new IllegalDate();
		}else{ // if valid, create the object
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}
	}
	
	public CalendarDateTimeRange difference(CalendarDateTime date){
		// first get date difference
		CalendarDateRange dateRange = super.difference(date);
		int years = dateRange.years;
		int days = dateRange.days;
		// then get second difference on current day
		int seconds = this.secondOfDay() - date.secondOfDay();
		// reconcile signs (make sure they are all positive or negative)
		if((days < 0 || years < 0) && seconds > 0){
			// invert seconds to negative value
			seconds = (CalendarDateTime.SECONDS_IN_DAY + date.secondOfDay() - this.secondOfDay()) * -1;
			// add a day
			days++;
			if(days > 0){
				years++;
				days = numOfDaysInYear(this.year) * -1; 
			}
		}else if((days > 0 || years > 0) && seconds < 0){
			seconds = (CalendarDateTime.SECONDS_IN_DAY - date.secondOfDay() + this.secondOfDay());
			days--;
			if(days < 0){
				years--;
				days = numOfDaysInYear(this.year); 
			}
			
		}
		
		// convert back into hours / minutes / seconds
		int hours = 0;
		int minutes = 0;
		seconds = Math.abs(seconds);
		while(seconds > 3600){
			hours++;
			seconds -= 3600;
		}
		while(seconds > 60){
			minutes++;
			seconds -= 60;
		}
		if(days < 0){
			seconds *= -1;
			minutes *= -1;
			hours *= -1;
		}
		
		return new CalendarDateTimeRange(years, days, hours, minutes, seconds);
	}
	
	// display date time as string
	public String toString(){
		int dispHours = (hour > 12) ? hour - 12 : hour; // drop PM times by 12 hours
		dispHours = (hour == 0) ? 12 : hour; // between midnight and 1 AM
		String dispMins = (minute > 9) ? minute + "" : "0" + minute; // pad minutes with 0 if needed
		String dispSecs = (second > 9) ? second + "" : "0" + second; // pad seconds with 0 if needed
		String merMark = (hour >= 12) ? "PM" : "AM"; // set meridian mark according to hours
		return super.toString() + ", " + dispHours + ":" + dispMins + ":" + dispSecs + merMark;  
	}
	
	// return an int that is the second number in the current day
	int secondOfDay(){
		return (((hour * 60) + minute) * 60) + second;
	}
	
}
