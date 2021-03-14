package data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.Books;

public class DataSQL {
	private java.sql.Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public void AddBooks(String authorName,String bookTitle, String isbn ,String category) {
		try {
			String sql = "INSERT INTO book"
					+ "(Authour_Name, Book_Title, ISBN , Category)"
					+ "VALUES (?,?,?,?)";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Ra01153832544");
			pst = con.prepareStatement(sql);
			pst.setString(1,authorName);
			pst.setString(2,bookTitle);
			pst.setString(3,isbn);
			pst.setString(4, category);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Inserted data successfuly");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	
	public ArrayList<Books> DisplayBooks(String isbn) {
		ArrayList<Books> book = new ArrayList<Books>();
		try {
			String sql = "Select * FROM book WHERE ISBN = ?";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Ra01153832544");
			pst = con.prepareStatement(sql);
			pst.setString(1, isbn);
			rs = pst.executeQuery();
			while(rs.next()) {
			Books bk = new Books();
			bk.setAuthorName(rs.getString("Authour_Name")); 
		    bk.setBookTitle(rs.getString("Book_Title")); 
		    bk.setIsbn(rs.getLong("ISBN")); 
		    bk.setCategory(rs.getString("Category")); 
		    
		    book.add(bk);
			}
			
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		return book;
		}
}
