package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import datasource.DataSQL;
import datasource.TextIO;

public class UpdateBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2480056089746072941L;
	private JPanel contentPane;
	private JTextField txtAuthorName;
	private JTextField txtBookTitle;
	private JTextField txtIsbn;
	private JComboBox<String> comboBoxCategory;

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
				} // ;
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 974, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAuthorName = new JLabel("Author Name :");
		lblAuthorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthorName.setBounds(54, 232, 148, 35);
		contentPane.add(lblAuthorName);

		JLabel lblBookTitle = new JLabel("Book Title :");
		lblBookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBookTitle.setBounds(60, 329, 120, 35);
		contentPane.add(lblBookTitle);

		JLabel lblIsbn = new JLabel("ISBN :");
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIsbn.setBounds(60, 37, 120, 35);
		contentPane.add(lblIsbn);

		txtAuthorName = new JTextField();
		txtAuthorName.setColumns(10);
		txtAuthorName.setBounds(394, 237, 215, 29);
		contentPane.add(txtAuthorName);

		txtBookTitle = new JTextField();
		txtBookTitle.setColumns(10);
		txtBookTitle.setBounds(394, 334, 298, 29);
		contentPane.add(txtBookTitle);

		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(408, 42, 133, 29);
		contentPane.add(txtIsbn);

		comboBoxCategory = new JComboBox<String>();
		comboBoxCategory.setBounds(343, 127, 362, 35);
		contentPane.add(comboBoxCategory);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean result = DataSQL.UpdateBooks(txtAuthorName.getText(), txtBookTitle.getText(),
						comboBoxCategory.getSelectedItem() + "", Long.parseLong(txtIsbn.getText()));
				if (result) {
					JOptionPane.showMessageDialog(null, "Update data successfuly");
				} else {
					JOptionPane.showMessageDialog(null, "Error while updating the book");
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
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnCancel.setBounds(524, 445, 133, 51);
		contentPane.add(btnCancel);

		JLabel lblNewLabel_1 = new JLabel("Category :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 125, 120, 35);
		contentPane.add(lblNewLabel_1);

	}
}
