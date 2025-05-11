import java.time.DateTimeException;
import java.time.LocalDate;

public class Date {


public int day;
public int month;
public int year;



public Date(int year,int month,int day){
	
	this.year=year;
	this.month=month;
	this.day=day;
	
}

public int getDay(){
	return day;
}

public int getMonth(){
	return month;
}
public int getYear(){
	return year;
}



public long getTime() {
	return(long) (year * 365 + month*30 + day);
}

public static Date today(){
	LocalDate now = LocalDate.now();
	return new Date(now.getDayOfMonth(),now.getMonthValue(),now.getYear());
	
}

public static void DateValidator(int year, int month , int day) throws NotValidDateException {
	try {
		LocalDate.of(year, month, day);
	}
	catch(DateTimeException exception){
		throw new NotValidDateException("Invalid date entered : " + day + "/" + month + "/" + year);
	}
}
public String toString() {
	return String.format("%d.%d.%d", day, month , year);
}
}