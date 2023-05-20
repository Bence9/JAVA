package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;

public class Menu extends JFrame {

	DbMethods dbm=new DbMethods();
	private JPanel contentPane;
	private EmpTM etm;
	private KonyvTM ktm;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		dbm.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 0));
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Könyv");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Beszúr");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeszurKonyv be = new BeszurKonyv(Menu.this);
				be.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem);
		
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Lista");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ktm = dbm.ReadAllKonyv();
				KonyvList el = new KonyvList(Menu.this, ktm);
				el.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		
		Object kmptmn[] = {"Azonosító","Cím","Kategória","Megjelenés","Ár","VevőID"};
		ktm = new KonyvTM(kmptmn, 0);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Töröl");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TorolKonyv to = new TorolKonyv(Menu.this);
				to.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Módosít (ár)");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModositKonyv mo = new ModositKonyv(Menu.this);
				mo.setVisible(true);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Keresés (ár)");
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeresKonyv ke = new KeresKonyv(Menu.this);
				ke.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_8);
		
		
		JMenu mnNewMenu_1 = new JMenu("Vevő");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);
		
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Beszúr");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeszurVevo be = new BeszurVevo(Menu.this);
				be.setVisible(true);
			}
		});
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Lista");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etm = dbm.ReadAllVevo();
				EmpList el = new EmpList(Menu.this, etm);
				el.setVisible(true);
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Töröl");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TorolVevo to = new TorolVevo(Menu.this);
				to.setVisible(true);
			}
		});
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Módosít (város)");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModositVevo mo = new ModositVevo(Menu.this);
				mo.setVisible(true);
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Keresés(város)");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeresVevo ke = new KeresVevo(Menu.this);
				ke.setVisible(true);
			}
		});
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_9);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 10, 10));

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/konyvesbolt.png")));
		lblNewLabel.setBounds(186, 0, 600, 271);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(537, 328, 125, 50);
		contentPane.add(btnNewButton);
		
		
		Object emptmn[] = {"ID","Név","Város"};
		etm = new EmpTM(emptmn, 0);
		
	}
}
