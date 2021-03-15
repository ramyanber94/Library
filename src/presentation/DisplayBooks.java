package presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import datasource.DataSQL;
import model.Book;

public class DisplayBooks extends JFrame implements Runnable, ThreadFactory {

	private JPanel contentPane;
	public JTextArea txtDisplay;
	private JTextArea txtParcode;
	private static final long serialVersionUID = 6441489157408381878L;

	public void run() {
		try {
			DisplayBooks frame = new DisplayBooks();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public DisplayBooks() {

		setTitle("Display Books");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1301, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(671, 405, -627, -258);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(75, 133, 795, 307);
		contentPane.add(scrollPane_1);

		txtDisplay = new JTextArea();
		scrollPane_1.setViewportView(txtDisplay);
		txtDisplay.setEditable(false);
		txtDisplay.setFont(new Font("Monospaced", Font.BOLD, 15));

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Book> book = DataSQL.DisplayBooks(txtParcode.getText());
				for (int i = 0; i < book.size(); i++) {
					txtDisplay.append("Book Title: " + book.get(i).getBookTitle() + "," + "Author Name: "
							+ book.get(i).getAuthorName() + "," + "ISBN: " + book.get(i).getIsbn());
					txtDisplay.append("\n");
				}

			}
		});
		btnFind.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnFind.setBounds(286, 486, 179, 51);
		contentPane.add(btnFind);

		txtParcode = new JTextArea();
		txtParcode.setBounds(427, 69, 220, 22);
		contentPane.add(txtParcode);

		JLabel lblNewLabel_1 = new JLabel("Barcode");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(186, 67, 93, 25);
		contentPane.add(lblNewLabel_1);

		JButton btnCamera = new JButton("Camera Scanner");
		btnCamera.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camera cm = new Camera(txtDisplay);
				cm.setVisible(true);
			}
		});
		btnCamera.setBounds(965, 256, 190, 92);
		contentPane.add(btnCamera);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnCancel.setBounds(754, 486, 179, 51);
		contentPane.add(btnCancel);

	}

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		return null;
	}
}
