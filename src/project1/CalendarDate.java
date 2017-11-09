package project1;

import java.text.DateFormatSymbols;

public class CalendarDate {
	int year;
	int month;
	int day;
	static int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

	// default constructor
	public CalendarDate(){
		year = 2012;
		month = 1;
		day = 1;
	}

	// constructor that takes year, month, and day and creates an object
	public CalendarDate(int year, int month, int day) throws IllegalDate{
		handleLeapYear(year);
		if(year < 0){
			throw new IllegalDate();
		}else if(month < 1 || month > 12){// month must be between 1 and 12 (inclusive)
			throw new IllegalDate();
		}else if(day <= 0 || day > daysInMonth[month - 1]){// day must be inside month
			throw new IllegalDate();
		}else{
			// if everything is valid, create the object
			this.year = year;
			this.month = month;
			this.day = day;
		}
	}
	
	// returns the next date as a string
	public String next(){
		// try to add one to the day
		try{
			return new CalendarDate(year, month, day + 1).toString();
		}catch(IllegalDate e){}
		// try to add one to the month
		try{
			return new CalendarDate(year, month + 1, 1).toString();
		}catch(IllegalDate e){}
		// try to add one to the year
		try{
			return new CalendarDate(year + 1, 1, 1).toString();
		}catch(IllegalDate e){}
		return null;
	}

	// returns the previous date as a string
	public String previous(){
		// try to subtract one from the day
		try{
			return new CalendarDate(year, month, day - 1).toString();
		}catch(IllegalDate e){}
		// try to subtract one from the month
		try{
			
			return new CalendarDate(year, month - 1, daysInMonth[month - 2]).toString();
		}catch(IllegalDate e){}
		// try to subtract one from the year
		try{
			return new CalendarDate(year - 1, 12, 31).toString();
		}catch(IllegalDate e){}
		return null;
	}
	
	// returns the name of the day as a string
	public String getDayName(){
		// first get number of days since Jan 1, 0001
		//get days since Jan 1 of current year
		int days = dayOfYear();
		//then add days for all previous years
		for(int i = year - 1; i > 0; i--){
			days += numOfDaysInYear(i);
		}
		// now use the Jan 1, 0001 as an absolute reference point to figure out the day
		return dayOfWeek[(days) % 7 ];
		
	}
	
	// returns string representation of object
	public String toString(){
		//referenced https://stackoverflow.com/questions/5799140/java-get-month-string-from-integer
		return new DateFormatSymbols().getMonths()[month-1] + " " + day + ", " + year;
	}
	
	// returns a two element array with years and days difference, positive if this is greater, negative if date is greater
	public CalendarDateRange difference(CalendarDate date){
		// get difference between two dates in days irrespective of year
		int days = this.dayOfYear() - date.dayOfYear();
		// get year difference between two dates 
		int	years = this.year - date.year;
		// now check for negative days and positive years, then we need to carry a year
		if(days < 0 && years > 0){ // negative days, positive years, decrement years and add days
			years -= 1;
			days = this.dayOfYear() + numOfDaysInYear(date.year) - date.dayOfYear();
		}
		return new CalendarDateRange(years, days);
	}
	
	// returns an int for the day number in the given year it is
	int dayOfYear(){
		handleLeapYear(year);
		int days = 0;
		//add days for all past months
		for(int i = 0; i < month - 1; i++){
			days += daysInMonth[i];
		}
		//add days in current month
		days += day;
		return days;
	}
	
	// returns the number of days in a given year
	static int numOfDaysInYear(int year){
		if(isLeapYear(year)){
			return 366;
		}else{
			return 365;
		}
	}
	
	// returns a boolean of whether the given year is a leap year
	static boolean isLeapYear(int year){
		// is leap year if year is divisible by 4 and not 100 or if it's divisible by 400
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
	
	// set number of days in February appropriately based on leap year or not
	static void handleLeapYear(int year){
		if(isLeapYear(year)){
			daysInMonth[1] = 29;
		}else{
			daysInMonth[1] = 28;
		}
	}
	
}
