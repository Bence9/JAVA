package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KeresKonyv extends JFrame {

	private JPanel contentPane;

	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	private JTextField tfAr1;
	private JTextField tfAr2;
	private ArkeresTM artm;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public KeresKonyv(JFrame f) {
		super("Ár szerint keresés");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 287);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Alsó határ:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(24, 43, 80, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblFelsHatr = new JLabel("Felső határ:");
		lblFelsHatr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFelsHatr.setBounds(24, 104, 80, 33);
		contentPane.add(lblFelsHatr);
		
		tfAr1 = new JTextField();
		tfAr1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAr1.setBounds(114, 43, 122, 26);
		contentPane.add(tfAr1);
		tfAr1.setColumns(10);
		
		tfAr2 = new JTextField();
		tfAr2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAr2.setColumns(10);
		tfAr2.setBounds(115, 106, 122, 26);
		contentPane.add(tfAr2);
		
		JButton btnNewButton = new JButton("Keres");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.goodInt(tfAr1, "Ar1"))
					if(c.goodInt(tfAr2, "Ar2"))
						if(JtfToInt(tfAr1) < JtfToInt(tfAr2)) {
							artm=dbm.ArLista(RTF(tfAr1), RTF(tfAr2));
							ArkeresList el = new ArkeresList(KeresKonyv.this, artm);
							el.setVisible(true);
						}else {
							dbm.SM3("Az alsó határ nagyobb a felső határnál!");
						}		
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(24, 181, 94, 41);
		contentPane.add(btnNewButton);
		
		
		Object artmn[] = {"Azonosító","Cím","Kategória","Megjelenés","Ár","VevőID"};
		artm = new ArkeresTM(artmn, 0);
		
		
		JButton btnBezr = new JButton("Bezár");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBezr.setBounds(193, 181, 94, 41);
		contentPane.add(btnBezr);
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public int JtfToInt(JTextField jtf) {
		int s = Integer.parseInt(jtf.getText());
		return s;
	}

}
