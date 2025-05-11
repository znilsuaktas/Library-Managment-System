import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



public class Book extends LibraryMaterial implements LibraryData {
private String bookName;
private String bookISBNNumber;
private Date bookDueDate;
private double price;


private final double feeByDay = 50.0;


public Date getbookDueDate() {
	
	return bookDueDate;
}


public void setbookDueDate(Date bookDueDate) {
	
	this.bookDueDate=bookDueDate;
}



public String getBookName() {
	return bookName;
}


public void setBookName(String bookName) {
	this.bookName = bookName;
}


public String getBookISBNNumber() {
	return bookISBNNumber;
}


public void setBookISBNNumber(String bookISBNNumber) {
	this.bookISBNNumber = bookISBNNumber;
}


public double getPrice() {
	return price;
}


public void setPrice(double price) {
	this.price = price;
}

public Book(String bookName,String bookISBNNumber,Date bookDueDate,double price) {
	
	this.bookName=bookName;
	this.bookISBNNumber=bookISBNNumber;
	this.bookDueDate=bookDueDate;
	this.price=price;
	
}



public String getISBNNumber() {
	return bookISBNNumber;
	
}


@Override
public double calculate_cost() {
	
  LocalDate now = LocalDate.now();
  LocalDate dueDate = LocalDate.of(bookDueDate.year, bookDueDate.month, bookDueDate.day);
  long overDue= ChronoUnit.DAYS.between(dueDate, now);

  if(overDue <= 0) {
	  return price;
  }
  
	
	return overDue*feeByDay;
	
}

public static void authenticateISBN(String  bookISBNNumber ) throws ISBNMismatchException{
	
	if(bookISBNNumber.length() != 13) {
		throw new ISBNMismatchException("ISBN must be 13 digits !!!");
	}
	
	else {
	int factor=0;
	int total=0;
	for (int i=0;i<12;i++) {
		if (i % 2 == 0) {
			factor=1;
		}
		else {
			factor=3;
		}
		int number=bookISBNNumber.charAt(i)-'0';
		total+=number*factor;
	}
	int checksum1 = 10 - (total % 10);
	if(checksum1 == 10) {
		checksum1 = 0;
	}
	int checksum2 = bookISBNNumber.charAt(12)- '0';
	
	
	
	if(checksum1 != checksum2) {
		throw new ISBNMismatchException("Invalid ISBN has been entered, checksum failed !!! :" + bookISBNNumber);
	}
	}
}



@Override
public String toString(){
	return "Book's name is: " + bookName + "\n " +
            "Book's ISBN Number is: "+  bookISBNNumber + "\n " + 
			"Book's due date is:  "+ bookDueDate + "\n" + 
            "Book's price is:" + price  ;
}











}
