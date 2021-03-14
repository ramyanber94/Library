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
	private JComboBox comboBoxCategory;
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
				
				
					com.google.zxing.Result result = null;
					BufferedImage image = null;
					
					if (web.isOpen()) {
						if ((image = web.getImage()) == null) {
							return;
						}
					}
					
					BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
					try {
						result = new MultiFormatReader().decode(bitmap);
					}catch (NotFoundException e) {
						
					}
					
					if (result != null) {
						txtParcode.setText(result.getText());
						Parcode();
					}
				
			}
			
			public void Parcode() {
				try {
					String iSbn = txtParcode.getText();
					txtDisplay.setText("");
					String sql = "Select * FROM book WHERE ISBN = ?";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, iSbn);
					rs = pst.executeQuery();
					String bookId = "";
					String authorName = "";
					String bookTitle = "";
					String isbn = "";
					String category = "";
					while(rs.next()) {
					bookId = rs.getString("BookID");
					authorName = rs.getString("Authour_Name");
				    bookTitle = rs.getString("Book_Title");
				    isbn = rs.getString("ISBN");
				    category = rs.getString("Category");
				    txtDisplay.append("Book_ID: " + bookId +","+ "Author Name: " + authorName+"," + "Book Title: " + bookTitle+" " + "ISBN: " + isbn+"," + "Category: " +  category);
					txtDisplay.append("\n");
					}
					
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}

	/**
	 * Create the frame.
	 */
	public DisplayBooks() {
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
		setTitle("Display Books");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1301, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(671, 405, -627, -258);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 161, 795, 307);
		contentPane.add(scrollPane_1);
		
		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);
		scrollPane_1.setViewportView(txtDisplay);
		txtDisplay.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("Category :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(98, 49, 120, 35);
		contentPane.add(lblNewLabel);
		
		java.awt.Dimension size = WebcamResolution.QVGA.getSize();
		
		web = Webcam.getWebcams().get(0);
		web.setViewSize(size);
		
		WebcamPanel panel = new WebcamPanel(web);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(873, 0, 308, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		web.addWebcamListener(new WebcamListener () {
			public void actionPerformed(ActionEvent e) {
				
			}

			@Override
			public void webcamClosed(WebcamEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void webcamDisposed(WebcamEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void webcamImageObtained(WebcamEvent arg0) {
				// TODO Auto-generated method stub
				run();
			}

			@Override
			public void webcamOpen(WebcamEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(385, 49, 362, 35);
		contentPane.add(comboBoxCategory);
		
		
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Parcode();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnFind.setBounds(427, 486, 179, 51);
		contentPane.add(btnFind);
		
		txtParcode = new JTextArea();
		txtParcode.setEditable(false);
		txtParcode.setBounds(933, 338, 220, 22);
		contentPane.add(txtParcode);
		
		JLabel lblNewLabel_1 = new JLabel("Parcode");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(989, 303, 93, 25);
		contentPane.add(lblNewLabel_1);
		
		
	
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "My Thread");
		t.setDaemon(true);
		return null;
	}
}
