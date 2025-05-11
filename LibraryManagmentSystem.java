import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LibraryManagmentSystem {

static ArrayList<Book> bookArray = new ArrayList<>();
static ArrayList<OnlineArticle> onlineArticleArray = new ArrayList<>();
static ArrayList<RegularMember> regularArray = new ArrayList<>();



static int articleIndex=0;


static int readerIndex=0;

public static void main(String[] args) {
	int choice=0;
	MenuOptions selectedChoice=null;
	

	boolean run = true;
	while(run) {
	Scanner libraryScan= new Scanner(System.in);
	System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+"Welcome to the Library Management Program !!!");
	System.out.println(ConsoleColors.YELLOW
			
			            + "1. Add a new book\n"
			            + "2. Add a new online article\n"
			            + "3. Create a member account \n"
			            + "4. Check out a book\n"
			            + "5. Return a book\n "
			            + "6. Give access to an online article\n"
			            + "7. End an online article access\n"
			            + "8. Display all accounts\n"
			            + "9. Display library database\n"
			            + "10. Members with overdue payments (descending order)\n"
			            + "11. Exit the Program");
	
	System.out.println(ConsoleColors.BROWN+"Please enter your choice (1-11)");
	 choice=libraryScan.nextInt();
	 
	 if(choice==1) selectedChoice= MenuOptions.ADD_BOOK;
	 else if(choice==2) selectedChoice=MenuOptions.ADD_ARTICLE;
	 else if(choice==3) selectedChoice=MenuOptions.ADD_READER;
	 else if(choice==4) selectedChoice=MenuOptions.CHECKED_OUT_BOOKS;
	 else if(choice==5) selectedChoice=MenuOptions.RETURN_BOOK;
	 else if(choice==6) selectedChoice=MenuOptions.ACCESSED_ARTICLES;
	 else if(choice==7) selectedChoice=MenuOptions.END_ARTICLE_ACCSESS;
	 else if(choice==8) selectedChoice=MenuOptions.DISPLAY_ACCOUNTS;
	 else if(choice==9) selectedChoice=MenuOptions.DISPLAY_DATABASE;
	 else if(choice==10)selectedChoice=MenuOptions.OVERDUE_PAYMENTS;
	 else if(choice==11) selectedChoice=MenuOptions.EXIT_PROGRAM;
	 
	 else System.out.println(ConsoleColors.RED_BOLD+"Invalid choice ! Please enter a number between 1-11 !!!");
	 
	 
	 switch (selectedChoice) {
		case ADD_BOOK:
			if(bookArray.size() < 10) {
					
					System.out.println(ConsoleColors.PURPLE+"Enter book name:");
					libraryScan.nextLine();	
					String bookName = libraryScan.nextLine();
					String bookISBNNumber = null;
					while(true) {
					System.out.println(ConsoleColors.PURPLE+"Enter ISBN number:");
					bookISBNNumber = libraryScan.nextLine();
					
					try {
						Book.authenticateISBN(bookISBNNumber);
						break;
					}catch(ISBNMismatchException exception) {
						System.out.println(ConsoleColors.RED_BOLD+"ISBNMismatch Exception :" + exception.getMessage());
					}
					}
					
					System.out.println(ConsoleColors.PURPLE+"Enter price of the book:");
					double bookPrice = libraryScan.nextDouble();
					libraryScan.nextLine();
					
					
								
					
					Book newBook= new Book(bookName, bookISBNNumber, Date.today(), bookPrice);
					bookArray.add(newBook);
					
			 System.out.println(ConsoleColors.GREEN_BOLD+"Your book is added to the library succesfully !!!");
				}
			else {
				System.out.println(ConsoleColors.RED_UNDERLINED+"You've reached the maximum amount of books !!! ");
			}
			break;
		case ADD_ARTICLE:
			if(onlineArticleArray.size() < 10) {
				
				System.out.println(ConsoleColors.PURPLE+"Enter the name of the Article:");
				libraryScan.nextLine();	
				String nameOfArticle = libraryScan.nextLine();
				
				System.out.println(ConsoleColors.PURPLE+"Enter DOI of the Article:");
				String DOI = libraryScan.nextLine();
				
				System.out.println(ConsoleColors.PURPLE+"Enter publisher name : (ACM/IEEE/Other)");
				String publisherName=libraryScan.nextLine();
				
				
				OnlineArticle newArticle= new OnlineArticle(nameOfArticle,DOI,publisherName,Date.today());
				onlineArticleArray.add(newArticle);
			
				System.out.println(ConsoleColors.GREEN_BOLD+"Your Article is added to the library succesfully !!!");
			}
		else {
			System.out.println(ConsoleColors.RED_UNDERLINED+"You've reached the maximum amount of articles !!! (10/10)");
		}
		break;
		case ADD_READER:
			System.out.println(ConsoleColors.PURPLE+"Please enter the type of member you would like to create: \r\n"
					+ "1.  Regular Member \r\n"
					+ "2.  Student Member \r\n"
					+ "3.  Academic Member");
			int memberChoice=libraryScan.nextInt();
			
			
			if(memberChoice==1) {
				System.out.println(ConsoleColors.PURPLE+"Enter new Regular member name:");
				libraryScan.nextLine();	
				String readerName = libraryScan.nextLine();
				System.out.println(ConsoleColors.ROSY_PINK+"Enter new Regular member ID:");
				int ID=libraryScan.nextInt();
				libraryScan.nextLine();
				System.out.println(ConsoleColors.GREEN_BOLD+"Member is added succesfully !!!");
				RegularMember newReader= new RegularMember(readerName,ID);
				regularArray.add(newReader);
				
			}
			if(memberChoice==2) {
				System.out.println(ConsoleColors.PURPLE+"Enter new Student member name:");
				libraryScan.nextLine();	
				String readerName = libraryScan.nextLine();
				System.out.println(ConsoleColors.ROSY_PINK+"Enter new Student member ID:");
				int ID=libraryScan.nextInt();
				libraryScan.nextLine();
				System.out.println(ConsoleColors.GREEN_BOLD+"Member is added succesfully !!!");
				Student newReader= new Student(readerName,ID);
				regularArray.add(newReader);
			}
			if(memberChoice==3) {
				
					System.out.println(ConsoleColors.PURPLE+"Enter new Academic member name:");
					libraryScan.nextLine();	
					String readerName = libraryScan.nextLine();
					System.out.println(ConsoleColors.ROSY_PINK+"Enter new Academic member ID:");
					int ID=libraryScan.nextInt();
					libraryScan.nextLine();
					System.out.println(ConsoleColors.GREEN_BOLD+"Member is added succesfully !!!");
					Academic newReader= new Academic(readerName,ID);
					regularArray.add(newReader);
					
				
			}
				
			
			
			
		break;
		
		case CHECKED_OUT_BOOKS:
			RegularMember selectedReader=null;
			Book selectedBook=null;
			
			System.out.println(ConsoleColors.ROSY_PINK+"Enter your ID:");
			
			int memberID=libraryScan.nextInt();
			libraryScan.nextLine();
			for(int i=0;i<regularArray.size();i++) {
				if(regularArray.get(i).getID()==memberID) {
					selectedReader=regularArray.get(i);
					break;
			}
			}
			if(selectedReader==null) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"Invalid ID !!!");
			}
			
			else {
				String ISBN = null;
				while(true) {
				System.out.println("Enter ISBN of the Book :");
				ISBN = libraryScan.nextLine();
				try {
					Book.authenticateISBN(ISBN);
					break;
				}catch(ISBNMismatchException exception) {
					System.out.println(ConsoleColors.RED_BOLD+"ISBNMismatch Exception :" + exception.getMessage());
				}
				}
				for(int i=0;i<bookArray.size();i++) {
				 if(bookArray.get(i).getISBNNumber().equals(ISBN)) {
				    selectedBook=bookArray.get(i);
				    break;
				 }
				
				}
				if(selectedBook==null) {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"“There is no book with that ISBN Number !!!");
					
			          }
				else {
					
					
					 Date newDate = null;
					while(true) {
					try {
						System.out.println("Enter a due year (YYYY)");
						int year=libraryScan.nextInt();
						libraryScan.nextLine();
						
						System.out.println("Enter a due month (MM)");
						int month=libraryScan.nextInt();
						libraryScan.nextLine();
						
						System.out.println("Enter a due day (DD)");
						int day=libraryScan.nextInt();
						libraryScan.nextLine();
						
						Date.DateValidator(year, month, day);
						newDate = new Date(year,month,day);
						break;
						
						
					}
					catch(NotValidDateException exception) {
						System.out.println(ConsoleColors.RED_BOLD+"Date Exception !!! " + exception.getMessage());
						
					}
					}
					
					
					
					Book book = new Book(selectedBook.getBookName(),selectedBook.getBookISBNNumber(),newDate,selectedBook.getPrice());
					book.setbookDueDate(newDate);
					
					boolean works = selectedReader.addBook(book);
					
					if(works) {
					System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT+ "The book with name " + book.getBookName() +  "(ISBN Number:" + book.getBookISBNNumber()+ ") is checked out by the user" + " " + selectedReader.getmemberName());
				   }
				}
				}
		      break;
			
			
			
		case RETURN_BOOK:
			RegularMember selectedReader3= null;
			System.out.println(ConsoleColors.PURPLE_BOLD+"Enter your ID :");
			int returnmemberID = libraryScan.nextInt();
			libraryScan.nextLine();
			for(int i=0;i < regularArray.size();i++) {
				if(regularArray.get(i).getID()== returnmemberID) {
					selectedReader3=regularArray.get(i);
					break;
				}
			}
				if(selectedReader3 == null ) {
					System.out.println(ConsoleColors.DARK_RED+"There is no such ID in the system !!!");
				}
				else {
					System.out.println(ConsoleColors.PURPLE_BOLD+"Enter the ISBN number of the book being returned:");
					String returnmemberISBN=libraryScan.nextLine();
					while(true) {
					try {
						Book.authenticateISBN(returnmemberISBN);
						break;
					}catch(ISBNMismatchException exception) {
						System.out.println(ConsoleColors.RED_BOLD+ "ISBNMismatch Exception :" + exception.getMessage());
					}
					}
					selectedReader3.returnBook(returnmemberISBN);
					
				}
	
			
			break;
			
		case END_ARTICLE_ACCSESS:
			
			RegularMember selectedReader4=null;
			System.out.println(ConsoleColors.PURPLE_BOLD+"Enter your ID:");
			int accseesID= libraryScan.nextInt();
			libraryScan.nextLine();
			for(int i=0;i < regularArray.size();i++) {
				if(regularArray.get(i).getID()== accseesID) {
					selectedReader4=regularArray.get(i);
					break;
				}
			}
			if(selectedReader4== null) {
				System.out.println(ConsoleColors.DARK_RED+"There is no such ID in the system !!!");
			}
			else {
				System.out.println(ConsoleColors.PURPLE_BOLD+"Enter your DOI Number :");
				String returnmemberDOI=libraryScan.nextLine();
				selectedReader4.returnArticle(returnmemberDOI);
			}
			
		break;
			
		case ACCESSED_ARTICLES:
			RegularMember selectedReader2= null;
			OnlineArticle selectedArticle=null;
			
			System.out.println(ConsoleColors.PURPLE_BOLD+"Enter your ID:");
			
			int memberID2=libraryScan.nextInt();
			libraryScan.nextLine();
			
			for(int i=0;i<regularArray.size();i++) {
				if(regularArray.get(i).getID()==memberID2) {
					selectedReader2=regularArray.get(i);
					break;
			}
			}
			if(selectedReader2==null) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT+" There is no account with that ID !!!");
			}
			else {
				System.out.println(ConsoleColors.PURPLE_BOLD+"Enter DOI of the Article :");
				String articleDOI = libraryScan.nextLine();
				for(int i=0;i<onlineArticleArray.size();i++) {
				 if(onlineArticleArray.get(i).getDOI().equals(articleDOI)) {
				    selectedArticle=onlineArticleArray.get(i);
				    break;
				 }
				
				}
				if(selectedArticle==null) {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT+ "There is no article with that DOI number !!!");
					
			          }
				else {
					
					Date accsessDate=null;
					while(true) {
					try {
						System.out.println("Enter the accses year of the article : (YYYY)");
						int accsessYear=libraryScan.nextInt();
						libraryScan.nextLine();
						
						System.out.println("Enter the accses month of the article : (MM)");
						int accsessMonth=libraryScan.nextInt();
						libraryScan.nextLine();
						
						System.out.println("Enter the accses day of the article : (DD)");
						int accsessDay=libraryScan.nextInt();
						libraryScan.nextLine();
						
						Date.DateValidator(accsessYear, accsessMonth, accsessDay);
						accsessDate = new Date(accsessYear,accsessMonth,accsessDay);
						break;
					}
					catch(NotValidDateException exception) {
						System.out.println(ConsoleColors.RED_BOLD+"Date Exception !!! " + exception.getMessage());
					}
					}
					
					
					
					selectedArticle.setAccsessDate(accsessDate);
					OnlineArticle oa = new OnlineArticle(selectedArticle.getNameofArticle(),selectedArticle.getDOI(),selectedArticle.getPublisher(),accsessDate);
					
					boolean works = selectedReader2.addOA(oa);
					if(works) {
					System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT+ "The online article entitled " + selectedArticle.getNameofArticle()  + "(DOI:" + selectedArticle.getDOI()+ ")  is accessed by the user" + " " +  selectedReader2.getmemberName());
					}
					}
		
			}
			break;
			
		case DISPLAY_DATABASE:
			
			if(bookArray.isEmpty()) {
				System.out.println(ConsoleColors.DARK_RED+"There is no book in the database !!! ");
			}
			else {
			for(int bookIndex=0;bookIndex<bookArray.size();bookIndex++) {
				Book newBook = bookArray.get(bookIndex);
			System.out.println(ConsoleColors.BLUE_BOLD+"Book's name is:" + newBook.getBookName());
			System.out.println(ConsoleColors.BLUE_BOLD+"Book's ISBN is :" + newBook.getISBNNumber());
			System.out.println(ConsoleColors.BLUE_BOLD+"The cost is :" + String.format("%.2f", newBook.calculate_cost()));
		    System.out.println();
			}
			}
			
			if(onlineArticleArray.isEmpty()) {
				System.out.println(ConsoleColors.DARK_RED+"There is no article in the database !!! ");
			}
			else {
			for(int articleIndex=0;articleIndex<onlineArticleArray.size();articleIndex++) {
		      OnlineArticle newArticle = onlineArticleArray.get(articleIndex);
		      System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Article's name :" + newArticle.getNameofArticle());
				System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Article's DOI is :" + newArticle.getDOI());
				System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"The cost is :" + String.format("%.2f", newArticle.calculate_cost()));
				System.out.println();
			}
			}
			
			break;
			
			
			
			
			
		case DISPLAY_ACCOUNTS:
			
			for(int i=0;i<regularArray.size();i++) {
				regularArray.get(i).displayInfo();
			}
			
			break;
			
		case OVERDUE_PAYMENTS:
			
			Collections.sort(regularArray);
			
			for(RegularMember user : regularArray ) {
				double total_cost=user.calculate_cost();
				    
				
				System.out.printf(ConsoleColors.PURPLE_BOLD+ "Member %s with user id %d is  due to pay %.2f ₺",user.getmemberName(),user.getID(), total_cost);
				System.out.println();
				
			}
			
			
			
			break;
			
		case EXIT_PROGRAM:
			run = false;
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"Exiting...Goodbye!!!");
			break;
		default:
			break;
	 }

	 
	 }
	}	
}