package SRP_01.Book;

public class Book {
    private String title;
    private String author;
    //... other properties;


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookSummary() {
        return this.getTitle() + " by " + this.getAuthor();
    }
}
