package presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import datasource.DataSQL;

public class RemoveBook extends JFrame {


	private static final long serialVersionUID = 3317976302754775643L;
	private JPanel contentPane;
	private JTextField txtIsbn;

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

		JLabel lblNewLabel = new JLabel("ISBN :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(57, 52, 113, 32);
		contentPane.add(lblNewLabel);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = DataSQL.DeleteBook(Long.parseLong(txtIsbn.getText()));
				if (result) {
				JOptionPane.showMessageDialog(null, "Delete data successfuly");
				} else {
					JOptionPane.showMessageDialog(null, "Error while deleting book");
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		btnRemove.setBounds(72, 124, 142, 39);
		contentPane.add(btnRemove);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnCancel.setBounds(299, 124, 142, 39);
		contentPane.add(btnCancel);

		txtIsbn = new JTextField();
		txtIsbn.setBounds(260, 61, 121, 19);
		contentPane.add(txtIsbn);
		txtIsbn.setColumns(10);
	}
}
