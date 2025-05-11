public class Reader {
private String readerName;
private int ID;
private Book checkedOutBook;
private OnlineArticle accessedArticle;


public String getreaderName() {
	return readerName;
}

public void setreaderName() {
	this.readerName=readerName;
}

public int getID() {
	return ID;
}

public void setID() {
	this.ID=ID;
}


public Book getcheckedOutBook() {
	return checkedOutBook;
}

public void setcheckedOutBook(Book checkedOutBook) {
	this.checkedOutBook=checkedOutBook;
}

public OnlineArticle getaccessedArticle() {
	return accessedArticle;
}

public void setaccessedArticle(OnlineArticle accessedArticle) {
	this.accessedArticle=accessedArticle;
}


public Reader(String readerName,int ID) {
	
	this.readerName=readerName;
	this.ID=ID;
}


}
