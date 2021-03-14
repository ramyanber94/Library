package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.TextIO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class UpdateBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtAuthorName;
	private JTextField txtBookTitle;
	private JTextField txtIsbn;
	private java.sql.Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private JComboBox comboBoxCategory;

	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					UpdateBook frame = new UpdateBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the frame.
	 */
	public UpdateBook() {
		addWindowListener(new WindowAdapter() {
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 974, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book ID :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(60, 38, 120, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthorName = new JLabel("Author Name :");
		lblAuthorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthorName.setBounds(46, 173, 148, 35);
		contentPane.add(lblAuthorName);
		
		JLabel lblBookTitle = new JLabel("Book Title :");
		lblBookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBookTitle.setBounds(60, 262, 120, 35);
		contentPane.add(lblBookTitle);
		
		JLabel lblIsbn = new JLabel("ISBN :");
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIsbn.setBounds(60, 352, 120, 35);
		contentPane.add(lblIsbn);
		
		txtAuthorName = new JTextField();
		txtAuthorName.setColumns(10);
		txtAuthorName.setBounds(394, 173, 215, 29);
		contentPane.add(txtAuthorName);
		
		txtBookTitle = new JTextField();
		txtBookTitle.setColumns(10);
		txtBookTitle.setBounds(394, 262, 298, 29);
		contentPane.add(txtBookTitle);
		
		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(394, 357, 133, 29);
		contentPane.add(txtIsbn);
		
		JSpinner spinBookID = new JSpinner();
		spinBookID.setBounds(394, 48, 53, 20);
		contentPane.add(spinBookID);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(341, 100, 362, 35);
		contentPane.add(comboBoxCategory);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int bookId = (Integer) spinBookID.getValue();
					String sql = "UPDATE book SET Authour_Name = ?, Book_Title = ?, ISBN = ?, Category = ? WHERE BookID = ?";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtAuthorName.getText());
					pst.setString(2, txtBookTitle.getText());
					pst.setString(3, txtIsbn.getText());
					pst.setString(4, comboBoxCategory.getSelectedItem()+"");
					pst.setInt(5,bookId);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update data successfuly");
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		btnUpdate.setBounds(196, 445, 133, 51);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnCancel.setBounds(524, 445, 133, 51);
		contentPane.add(btnCancel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Category :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 100, 120, 35);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
