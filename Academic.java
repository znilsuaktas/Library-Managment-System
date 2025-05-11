import java.util.ArrayList;
import java.util.Iterator;

public class Academic extends RegularMember{

	
public Academic(String memberName,int ID) {
		
		super(memberName,ID);
		checkedOutBook = new ArrayList<Book>();
		accessableOnlineArticle = new ArrayList<OnlineArticle>();
		accessibleOnlineArticleCount=0;
		checkedOutBookCount=0;
		
	}

@Override
public boolean addBook(Book newBook) {
	
	if(checkedOutBook.size() < 3) {
		checkedOutBook.add(newBook);
		return true;
		
	}
	else {
		System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"You've reached the maximum capacity !!!");
		return false;
	}
	
}


@Override
public boolean addOA(OnlineArticle oa) {

if(accessableOnlineArticle.size()< 3) {
	accessableOnlineArticle.add(oa);
	return true;
	
}else {
	System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"You've reached the maximum capacity !!!");
	return false;
}

}

@Override
public void returnBook(String ISBN) {
	  super.returnBook(ISBN);
}

@Override
public void returnArticle(String DOI) {
	  super.returnArticle(DOI);
}

@Override
public double calculate_cost() {
	  
	  double total=0.0;
	  for(int costindex=0;costindex<checkedOutBook.size();costindex++) {
		  total+=checkedOutBook.get(costindex).calculate_cost();	
		  }
	  for(int costindex=0;costindex < accessableOnlineArticle.size();costindex++) {
		  total+=accessableOnlineArticle.get(costindex).calculate_cost();
	  }
	  
	  return total;
}
@Override
public void displayInfo() {
  System.out.println(ConsoleColors.GREEN_UNDERLINED+"Account type:Academic");
  super.displayInfo();
 
}
}