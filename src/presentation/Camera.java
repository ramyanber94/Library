package presentation;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

import datasource.DataSQL;
import model.Book;

public class Camera extends JFrame implements Runnable, ThreadFactory {

	private JPanel contentPane;
	private JTextField txtBarcode;
	private Webcam web = null;
	private static final long serialVersionUID = 6441489157408381878L;

	/**
	 * Launch the application.
	 */

	public void captureImageAndProcessBarcode() {

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
		} catch (NotFoundException e) {

		}

		if (result != null) {
			txtBarcode.setText(result.getText());
		}
	}

	/**
	 * Create the frame.
	 */
	public Camera(JTextArea mainFrameTextArea) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		java.awt.Dimension size = WebcamResolution.QVGA.getSize();

		web = Webcam.getWebcams().get(0);
		web.setViewSize(size);

		WebcamPanel panel = new WebcamPanel(web);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 10, 582, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		web.addWebcamListener(new WebcamListener() {

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
				captureImageAndProcessBarcode();

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

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Book> book = DataSQL.DisplayBooks(txtBarcode.getText());
				for (int i = 0; i < book.size(); i++) {
					mainFrameTextArea.append(book.get(i).toString());
					mainFrameTextArea.append("\n");
				}
				dispose();
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOK.setBounds(252, 323, 85, 21);
		contentPane.add(btnOK);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "My Thread");
		t.setDaemon(true);
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
