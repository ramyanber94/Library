package business;

public class Books {
	
	public static final int MAX_AUTHOR_NAME_SIZE = 20; 
	public static final int MAX_BOOK_TITLE_SIZE = 20;
	public static final int MAX_CATEGORY_SIZE = 10;
	private String authorName , bookTitle, category;
	private int isbn , bookId;
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
}
