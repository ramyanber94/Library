package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.aoju.bus.extra.qrcode.LuminanceSource;

import com.github.binarywang.utils.qrcode.BufferedImageLuminanceSource;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.Dimension;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.HybridBinarizer;
import com.mysql.cj.xdevapi.Result;

import business.Books;
import data.DataSQL;
import data.TextIO;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class DisplayBooks extends JFrame implements Runnable, ThreadFactory {
	
	private JPanel contentPane;
	private java.sql.Connection con = null;
	private JTextArea txtDisplay;
	private JTextArea txtParcode;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Webcam webcam = null;
	private static final long serialVersionUID = 6441489157408381878L;
	private Executor executor = Executors.newSingleThreadExecutor(this);
	
	/**
	 * Launch the application.
	 */
	private Webcam web = null;
	
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
				DataSQL ds = new DataSQL();
				String isbn ="";
				ArrayList<Books> book = ds.DisplayBooks(txtParcode.getText());
				for(int i = 0; i < book.size(); i++) {
				txtDisplay.append("Book Title: " + book.get(i).getBookTitle() + "," + "Author Name: " + book.get(i).getAuthorName() + "," +
				"ISBN: " + book.get(i).getIsbn());
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
		
		JLabel lblNewLabel_1 = new JLabel("Parcode");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(186, 67, 93, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCamera = new JButton("Camera Scanner");
		btnCamera.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camera cm = new Camera();
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
