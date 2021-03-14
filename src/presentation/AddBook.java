package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.sun.jdi.connect.spi.Connection;

import business.Books;
import data.TextIO;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBook {

	public JFrame frame;
	private JTextField txtAuthorName;
	private JTextField txtBookTitle;
	private JTextField txtIsbn;
	private JComboBox comboBoxCategory;
	private java.sql.Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	

	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					AddBook window = new AddBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
		};

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public AddBook() throws ClassNotFoundException {
		initialize();
	}

	private void initialize() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					ArrayList<String> recs = TextIO.readRecords();
					for (int i = 0; i < recs.size(); i++) {

						comboBoxCategory.addItem(recs.get(i));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //;
			}
		});
		frame.setBounds(100, 100, 864, 521);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Category :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(65, 81, 120, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAuthorName = new JLabel("Author Name :");
		lblAuthorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthorName.setBounds(51, 159, 148, 35);
		frame.getContentPane().add(lblAuthorName);
		
		JLabel lblBookTitle = new JLabel("Book Title :");
		lblBookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBookTitle.setBounds(65, 248, 120, 35);
		frame.getContentPane().add(lblBookTitle);
		
		JLabel lblIsbn = new JLabel("ISBN :");
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIsbn.setBounds(65, 338, 120, 35);
		frame.getContentPane().add(lblIsbn);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(352, 81, 362, 35);
		frame.getContentPane().add(comboBoxCategory);
		
		txtAuthorName = new JTextField();
		txtAuthorName.setBounds(352, 159, 215, 29);
		frame.getContentPane().add(txtAuthorName);
		txtAuthorName.setColumns(10);
		
		txtBookTitle = new JTextField();
		txtBookTitle.setColumns(10);
		txtBookTitle.setBounds(352, 248, 298, 29);
		frame.getContentPane().add(txtBookTitle);
		
		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(352, 343, 133, 29);
		frame.getContentPane().add(txtIsbn);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String sql = "INSERT INTO book"
						+ "(Authour_Name, Book_Title, ISBN , Category)"
						+ "VALUES (?,?,?,?)";
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","");
				pst = con.prepareStatement(sql);
				pst.setString(1,txtAuthorName.getText());
				pst.setString(2,txtBookTitle.getText());
				pst.setString(3,txtIsbn.getText());
				pst.setString(4, comboBoxCategory.getSelectedItem()+"");
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Inserted data successfuly");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		btnNewButton.setBounds(189, 403, 133, 51);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnCancel.setBounds(517, 403, 133, 51);
		frame.getContentPane().add(btnCancel);
	}

}
