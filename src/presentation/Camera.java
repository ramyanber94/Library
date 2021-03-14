package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.binarywang.utils.qrcode.BufferedImageLuminanceSource;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Camera extends JFrame implements Runnable ,  ThreadFactory {

	private JPanel contentPane;
	private JTextField txtBarcode;
	private JComboBox comboBoxCategory;
	private java.sql.Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Webcam web = null;
	private static final long serialVersionUID = 6441489157408381878L;
	private Executor executor = Executors.newSingleThreadExecutor(this);

	/**
	 * Launch the application.
	 */


			public void run() {
				try {
					Camera frame = new Camera();
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
					txtBarcode.setText(result.getText());
				}
				}


	/**
	 * Create the frame.
	 */
	public Camera() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		 java.awt.Dimension size = WebcamResolution.QVGA.getSize();
		 
		 web = Webcam.getWebcams().get(0); web.setViewSize(size);
		 
		
		
		  WebcamPanel panel = new WebcamPanel(web); panel.setPreferredSize(size);
		  panel.setFPSDisplayed(true);
		  panel.setBackground(SystemColor.inactiveCaptionBorder); panel.setBounds(10, 10, 582, 246);
		  contentPane.add(panel); panel.setLayout(null);
		  
		  
		  
		  web.addWebcamListener(new WebcamListener () {

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
		  
		 
		
		JLabel lblNewLabel = new JLabel("Result");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(89, 270, 71, 28);
		contentPane.add(lblNewLabel);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(209, 276, 306, 19);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(252, 323, 85, 21);
		contentPane.add(btnNewButton);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "My Thread");
		t.setDaemon(true);
		return null;
	}
}
