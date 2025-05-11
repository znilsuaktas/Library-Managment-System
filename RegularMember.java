import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class RegularMember implements LibraryData, Comparable<RegularMember> {
protected String memberName;
protected int ID;
protected ArrayList<Book> checkedOutBook;
protected ArrayList<OnlineArticle> accessableOnlineArticle;

protected int accessibleOnlineArticleCount;
protected int checkedOutBookCount;


public String getmemberName() {
	return memberName;
}

public void setmemberName() {
	this.memberName=memberName;
}

public int getID() {
	return ID;
}

public void setID() {
	this.ID=ID;
}

public ArrayList<Book> getCheckedOutBook() {
	return checkedOutBook;
}

public void setCheckedOutBook(ArrayList<Book> checkedOutBook) {
	this.checkedOutBook = checkedOutBook;
}

public ArrayList<OnlineArticle> getAccessableOnlineArticle() {
	return accessableOnlineArticle;
}

public void setAccessableOnlineArticle(ArrayList<OnlineArticle> accessableOnlineArticle) {
	this.accessableOnlineArticle = accessableOnlineArticle;
}

public RegularMember(String memberName,int ID) {
	
	this.memberName=memberName;
	this.ID=ID;
	checkedOutBook= new ArrayList<Book>();
	accessableOnlineArticle= new ArrayList<OnlineArticle>();
}

public boolean addBook(Book newBook) {
	if(checkedOutBook.size()<1) {
		checkedOutBook.add(newBook);
		return true;
	}else {
		System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"The capacity is full !!!");
		return false;
	}
}

public boolean addOA(OnlineArticle oa) {
	if(accessableOnlineArticle.size()<1) {
		accessableOnlineArticle.add(oa);
		return true;
	}else {
		System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"The capacity is full !!!");
		return false;
	}
}


public void returnBook(String ISBN) {
	Iterator<Book> iteratorBook = checkedOutBook.iterator();
	while(iteratorBook.hasNext()) {
		Book newBook = iteratorBook.next();
		
		if(newBook.getISBNNumber().equals(ISBN)) {
			iteratorBook.remove();
			System.out.println("Book has been returned succsesfully !!!");
			
		}
		else {
			System.out.println("There is no such book in your account !!!");
		}
		
	}
	}

public void returnArticle(String DOI) {
	Iterator<OnlineArticle> iteratorArticle = accessableOnlineArticle.iterator();
	while(iteratorArticle.hasNext()) {
		OnlineArticle newArticle = iteratorArticle.next();
		if(newArticle.getDOI().equals(DOI)) {
			iteratorArticle.remove();
            System.out.println("Article has been returned succsesfully !!!");
			
		}
		else {
			System.out.println("There is no such article in your account !!!");
		}
		}
	}

@Override
public double calculate_cost() {

	double total=0.0;
	
	for(Book book : checkedOutBook) {
		total+=book.calculate_cost();
	}
	LocalDate now = LocalDate.now();
	for(OnlineArticle article : accessableOnlineArticle ) {
		LocalDate oaAccsessDate =LocalDate.of(article.getAccsessDate().getYear(),article.getAccsessDate().getMonth(),article.getAccsessDate().getDay());
		
		double oaCost = article.calculate_cost();
		long overDue = ChronoUnit.DAYS.between(oaAccsessDate, now);
		if(overDue > 30) {
			
		    oaCost += (overDue * 10);
		
		}
		
		    total += oaCost;
	}
	
	return total ;
}

@Override
public int compareTo(RegularMember otherMembers){
	
	return -Double.compare(this.calculate_cost(), otherMembers.calculate_cost());
}
	


public void displayInfo() {
	System.out.println(ConsoleColors.BLACK+"Member's name is: " +  memberName + "\n " +   "Member's ID is : "+ ID );
	System.out.println(ConsoleColors.GREEN_UNDERLINED+"Checked out books:");
	if(checkedOutBook.isEmpty()) {
	     System.out.println(ConsoleColors.DARK_RED+"None");
	}
	else {
	for(int index=0;index<checkedOutBook.size();index++) {
		Book newBook = checkedOutBook.get(index);
		System.out.println(ConsoleColors.BLACK+ newBook.toString());
		double totalCost = newBook.calculate_cost();
		 System.out.println(ConsoleColors.BLACK+"The book entitled " + newBook.getBookName() + 
				 "has an overdue charge of  " + String.format("%.2f",totalCost) + "₺");
		}
	}
	System.out.println(ConsoleColors.GREEN_UNDERLINED+"Obtained online articles:");
	if(accessableOnlineArticle.isEmpty()) {
		System.out.println(ConsoleColors.DARK_RED+"None");
	}
	else {
	for(int index=0;index<accessableOnlineArticle.size();index++) {
		OnlineArticle newArticle = accessableOnlineArticle.get(index);
		System.out.println(ConsoleColors.BLACK+ newArticle.toString());
		double totalCost = newArticle.calculate_cost();
		 System.out.println(ConsoleColors.BLACK+"The article entitled " +" " + newArticle.getNameofArticle() + 
				 " has an overdue charge of  " + String.format("%.2f",totalCost) + "₺");
		
		}
}

}

}