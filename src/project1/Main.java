package project1;

public class Main {

	/**
	 * @param args
	 * @throws IllegalDate 
	 */
	public static void main(String[] args) throws IllegalDate {
		// Object Testing
		CalendarDate d1 = new CalendarDate();
		CalendarDate d2 = new CalendarDate(2015, 2, 28);
		CalendarDate d3 = new CalendarDate(1900, 2, 28);
		CalendarDate d4 = new CalendarDate(2016, 2, 29);
		CalendarDate d5 = new CalendarDate(2015, 3, 1);
		CalendarDate d6 = new CalendarDate(2016, 3, 1);
		CalendarDate d7 = new CalendarDate(2017, 9, 5);
		CalendarDateRange diff1 = d4.difference(d2);
		CalendarDateRange diff2 = d2.difference(d4);
		CalendarDateRange diff3 = d4.difference(d5);
		System.out.println(d1);
		System.out.println(d1.next());
		System.out.println(d2);
		System.out.println(d2.getDayName());
		System.out.println(d2.next());
		System.out.println(d3);
		System.out.println(d3.getDayName());
		System.out.println(d3.next());
		System.out.println(d4);
		System.out.println(d4.next());
		System.out.println(d5);
		System.out.println(d5.getDayName());
		System.out.println(d5.previous());
		System.out.println(d6);
		System.out.println(d6.getDayName());
		System.out.println(d6.previous());
		System.out.println(d7);
		System.out.println(d7.getDayName());
		System.out.println("Difference: " + diff1.years + " years and " + diff1.days + " days.");
		System.out.println("Difference: " + diff2.years + " years and " + diff2.days + " days.");
		System.out.println("Difference: " + diff3.years + " years and " + diff3.days + " days.");
		// Lunar Eclipses
		CalendarDateTime lunar1 = new CalendarDateTime(2011, 12, 10, 14, 32, 56);
		CalendarDateTime lunar2 = new CalendarDateTime(2014, 4, 15, 7, 46, 48);
		CalendarDateTime lunar3 = new CalendarDateTime(2014, 10, 8, 10, 55, 44);
		CalendarDateTime lunar4 = new CalendarDateTime(2015, 4, 4, 12, 1, 24);
		CalendarDateTime lunar5 = new CalendarDateTime(2015, 9, 28, 2, 48, 17);
		CalendarDateTimeRange[] lunarRanges = new CalendarDateTimeRange[4];
		lunarRanges[0] = lunar5.difference(lunar4);
		lunarRanges[1] = lunar4.difference(lunar3);
		lunarRanges[2] = lunar3.difference(lunar2);
		lunarRanges[3] = lunar2.difference(lunar1);
		CalendarDateTimeRange totals = new CalendarDateTimeRange();
		for(int i = 0; i < lunarRanges.length; i++){
			totals.years += lunarRanges[i].years;
			totals.days += lunarRanges[i].days;
			totals.hours += lunarRanges[i].hours;
			totals.minutes += lunarRanges[i].minutes;
			totals.seconds += lunarRanges[i].seconds;
		}
		System.out.println("\nLunar Eclipses\n");
		System.out.println("Average years: " + totals.years / 5);
		System.out.println("Average days: " + totals.days / 5);
		System.out.println("Average hours: " + totals.hours / 5);
		System.out.println("Average minutes: " + totals.minutes / 5);
		System.out.println("Average seconds: " + totals.seconds / 5);
	}

}
