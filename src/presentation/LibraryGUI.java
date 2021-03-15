package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class LibraryGUI {

	private JFrame frmLibrary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryGUI window = new LibraryGUI();
					window.frmLibrary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibraryGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibrary = new JFrame();
		frmLibrary.setTitle("Library");
		frmLibrary.setBounds(100, 100, 790, 475);
		frmLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Welcome to the Library windows application");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		frmLibrary.getContentPane().add(lblNewLabel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		frmLibrary.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook b;

				b = new AddBook();
				b.frame.setVisible(true);

			}
		});
		mnNewMenu.add(mntmAdd);

		JMenuItem mntmRemove = new JMenuItem("Remove");
		mntmRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveBook rb = new RemoveBook();
				rb.setVisible(true);
			}
		});
		mnNewMenu.add(mntmRemove);

		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook ad = null;

				ad = new AddBook();

				DisplayBooks db = new DisplayBooks();
				RemoveBook rb = new RemoveBook();
				UpdateBook ub = new UpdateBook();
				ad.frame.dispose();
				db.dispose();
				rb.dispose();
				ub.setVisible(true);
			}
		});
		mnNewMenu.add(mntmUpdate);

		JMenuItem mntmNewMenuItem = new JMenuItem("Display");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook ad = null;

				ad = new AddBook();

				DisplayBooks db = new DisplayBooks();
				RemoveBook rb = new RemoveBook();
				UpdateBook ub = new UpdateBook();
				ad.frame.dispose();
				db.setVisible(true);
				rb.dispose();
				ub.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Services");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmBooking = new JMenuItem("Booking");
		mnNewMenu_1.add(mntmBooking);

		JMenuItem mntmBuy = new JMenuItem("Buy");
		mnNewMenu_1.add(mntmBuy);
	}

}
