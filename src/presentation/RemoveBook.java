package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RemoveBook extends JFrame {

	private JPanel contentPane;
	private java.sql.Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					RemoveBook frame = new RemoveBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the frame.
	 */
	public RemoveBook() {
		setTitle("Remove Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 498, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book ID :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(57, 52, 113, 32);
		contentPane.add(lblNewLabel);
		
		JSpinner spinBookID = new JSpinner();
		spinBookID.setBounds(346, 61, 60, 20);
		contentPane.add(spinBookID);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int BookID = (Integer) spinBookID.getValue();
					String sql = "DELETE FROM book WHERE BookID = ?";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","");
					pst = con.prepareStatement(sql);
					pst.setInt(1, BookID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Delete data successfuly");
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		btnRemove.setBounds(72, 124, 142, 39);
		contentPane.add(btnRemove);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnCancel.setBounds(299, 124, 142, 39);
		contentPane.add(btnCancel);
	}
}
