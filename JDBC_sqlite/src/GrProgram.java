import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrProgram extends JFrame {

	DbMethods dbm=new DbMethods();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrProgram frame = new GrProgram();
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
	public GrProgram() {
		dbm.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAbTeszt = new JButton("AB teszt");
		btnAbTeszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.DisConnect(dbm.Connect());
			}
		});
		btnAbTeszt.setBounds(10, 10, 85, 21);
		contentPane.add(btnAbTeszt);
		
		JButton btnBeszuras = new JButton("Beszúrás");
		btnBeszuras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCar ne=new NewCar(GrProgram.this);
				ne.setVisible(true);
			}
		});
		btnBeszuras.setBounds(10, 51, 103, 21);
		contentPane.add(btnBeszuras);
	}
}

