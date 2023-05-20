package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class BeszurVevo extends JFrame {

	private JPanel contentPane;
	private JTextField tfVevoID;
	private JTextField tfNev;
	private JTextField tfVaros;
	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BeszurVevo(JFrame f) {
		super("Vevő beszúrása");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VevoID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 43, 67, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblVros = new JLabel("Név:");
		lblVros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVros.setBounds(10, 82, 67, 29);
		contentPane.add(lblVros);
		
		JLabel lblVros_1 = new JLabel("Város:");
		lblVros_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVros_1.setBounds(10, 121, 67, 29);
		contentPane.add(lblVros_1);
		
		tfVevoID = new JTextField();
		tfVevoID.setBounds(65, 47, 130, 25);
		contentPane.add(tfVevoID);
		tfVevoID.setColumns(10);
		
		tfNev = new JTextField();
		tfNev.setColumns(10);
		tfNev.setBounds(52, 86, 182, 25);
		contentPane.add(tfNev);
		
		tfVaros = new JTextField();
		tfVaros.setColumns(10);
		tfVaros.setBounds(52, 124, 182, 25);
		contentPane.add(tfVaros);
		
		JButton btnNewButton = new JButton("Beszúr");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.goodInt(tfVevoID, "VevoID"))
					if(c.filled(tfNev, "nev"))
						if(c.filled(tfVaros, "varos"))
				dbm.InsertVevo(RTF(tfVevoID), RTF(tfNev), RTF(tfVaros));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(22, 192, 110, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Bezár");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(258, 192, 110, 41);
		contentPane.add(btnNewButton_1);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
