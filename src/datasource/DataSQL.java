package datasource;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;

public class DataSQL {
	private static java.sql.Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static java.sql.Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "Ra01153832544");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static boolean AddBooks(String authorName, String bookTitle, String isbn, String category) {
		try {
			String sql = "INSERT INTO book" + "(Authour_Name, Book_Title, ISBN , Category)" + "VALUES (?,?,?,?)";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, authorName);
			pst.setString(2, bookTitle);
			pst.setString(3, isbn);
			pst.setString(4, category);
			pst.executeUpdate();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Book> DisplayBooks(String isbn) {
		ArrayList<Book> book = new ArrayList<Book>();
		try {
			String sql = "Select * FROM book WHERE ISBN = ?";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, isbn);
			rs = pst.executeQuery();
			while (rs.next()) {
				Book bk = new Book();
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

	public static boolean UpdateBooks(String authorName, String bookTitle, String category, long isbn) {
		try {
			String sql = "UPDATE book SET Authour_Name = ?, Book_Title = ?, Category = ? WHERE ISBN = ?";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, authorName);
			pst.setString(2, bookTitle);
			pst.setString(3, category);
			pst.setLong(4, isbn);
			pst.executeUpdate();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean DeleteBook(long isbn) {
		try {
			String sql = "DELETE FROM book WHERE ISBN = ?";
			con = getConnection();
			pst = con.prepareStatement(sql);
			pst.setLong(1, isbn);
			pst.executeUpdate();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}