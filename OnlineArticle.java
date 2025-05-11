
public class OnlineArticle extends LibraryMaterial implements LibraryData {
private String nameofArticle;
private String DOI;
private String publisher;
private Date accsessDate;


public OnlineArticle(String nameofArticle,String DOI,String publisher,Date accsessDate) {
	
	this.nameofArticle=nameofArticle;
	this.DOI=DOI;
	this.publisher=publisher;
	this.accsessDate=accsessDate;
	
}

public OnlineArticle(String nameofArticle,String DOI,String publisher){
	
	this.nameofArticle=nameofArticle;
	this.DOI=DOI;
	this.publisher=publisher.trim();
	this.accsessDate= Date.today();
	
}


public String getNameofArticle() {
	return nameofArticle;
}

public void setNameofArticle(String nameofArticle) {
	this.nameofArticle = nameofArticle;
}

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

public void setDOI(String dOI) {
	DOI = dOI;
}

public String getDOI() {
	return DOI;
}


public Date getAccsessDate() {
	return accsessDate;
}

public void setAccsessDate(Date accsessDate) {
	this.accsessDate = accsessDate;
}

@Override
public double calculate_cost() {
	if(publisher.trim().equalsIgnoreCase("ACM")) {
	return 150.0;
	}
	else if(publisher.trim().equalsIgnoreCase("IEEE")) {
		return 200.0;
	}
	else {
		return 100.0;
	}
}
@Override
public String toString(){
	
	return "Name of the article is: " + nameofArticle+ "\n" + 
	        "DOI of the article is: "+ DOI + "\n" + 
			"Publisher of the article is :" + publisher + "\n" +
	        "Access Date of the article is :" + accsessDate;
}
}