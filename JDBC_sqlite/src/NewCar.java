import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCar extends JDialog {
	private JTextField jtfRendszam;
	private JTextField jtfTipus;
	private JTextField jtfSzin;
	private JTextField jtfKor;
	private JTextField jtfAr;
	DbMethods dbm = new DbMethods();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public NewCar(JFrame f) {
		super(f, "Autó beszúrása", true);
		
		setBounds(100, 100, 361, 292);
		getContentPane().setLayout(null);
		
		JLabel lblRendszam = new JLabel("Rendszam:");
		lblRendszam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRendszam.setBounds(10, 10, 69, 23);
		getContentPane().add(lblRendszam);
		
		JLabel lblTipus = new JLabel("Tipus:");
		lblTipus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipus.setBounds(10, 43, 69, 23);
		getContentPane().add(lblTipus);
		
		JLabel lblSzin = new JLabel("Szín:");
		lblSzin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSzin.setBounds(10, 76, 69, 23);
		getContentPane().add(lblSzin);
		
		JLabel lblKor = new JLabel("Kor:");
		lblKor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKor.setBounds(10, 109, 69, 23);
		getContentPane().add(lblKor);
		
		JLabel lblAr = new JLabel("Ár:");
		lblAr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAr.setBounds(10, 142, 69, 23);
		getContentPane().add(lblAr);
		
		jtfRendszam = new JTextField();
		jtfRendszam.setBounds(77, 13, 96, 19);
		getContentPane().add(jtfRendszam);
		jtfRendszam.setColumns(10);
		
		jtfTipus = new JTextField();
		jtfTipus.setColumns(10);
		jtfTipus.setBounds(77, 46, 96, 19);
		getContentPane().add(jtfTipus);
		
		jtfSzin = new JTextField();
		jtfSzin.setColumns(10);
		jtfSzin.setBounds(77, 79, 78, 19);
		getContentPane().add(jtfSzin);
		
		jtfKor = new JTextField();
		jtfKor.setColumns(10);
		jtfKor.setBounds(77, 109, 43, 19);
		getContentPane().add(jtfKor);
		
		jtfAr = new JTextField();
		jtfAr.setColumns(10);
		jtfAr.setBounds(77, 142, 96, 19);
		getContentPane().add(jtfAr);
		
		JButton btnBeszur = new JButton("Beszúr");
		btnBeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.Reg();
				dbm.Insert(RTF(jtfRendszam), RTF(jtfTipus), RTF(jtfSzin), RTF(jtfKor), RTF(jtfAr));
			}
		});
		btnBeszur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBeszur.setBounds(35, 211, 85, 21);
		getContentPane().add(btnBeszur);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBezar.setBounds(231, 211, 85, 21);
		getContentPane().add(btnBezar);

	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}

