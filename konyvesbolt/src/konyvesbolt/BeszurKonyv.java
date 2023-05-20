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

public class BeszurKonyv extends JFrame {

	private JPanel contentPane;
	private JTextField tfAzonosito;
	private JTextField tfCim;
	private JTextField tfKategoria;
	private JTextField tfMegjelenes;
	private JTextField tfAr;
	private JTextField tfVevoID;
	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BeszurKonyv(JFrame f) {
		super("Könyv beszúrása");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Azonosító:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 21, 89, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblCm = new JLabel("Cím:");
		lblCm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCm.setBounds(10, 57, 89, 29);
		contentPane.add(lblCm);
		
		JLabel lblKategria = new JLabel("Kategória:");
		lblKategria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKategria.setBounds(10, 96, 89, 29);
		contentPane.add(lblKategria);
		
		JLabel lblMegjelens = new JLabel("Megjelenés:");
		lblMegjelens.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMegjelens.setBounds(10, 135, 89, 29);
		contentPane.add(lblMegjelens);
		
		JLabel lblr = new JLabel("Ár:");
		lblr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblr.setBounds(10, 181, 89, 29);
		contentPane.add(lblr);
		
		JLabel lblVevid = new JLabel("VevőID:");
		lblVevid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVevid.setBounds(10, 220, 89, 29);
		contentPane.add(lblVevid);
		
		tfAzonosito = new JTextField();
		tfAzonosito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfAzonosito.setBounds(90, 27, 164, 23);
		contentPane.add(tfAzonosito);
		tfAzonosito.setColumns(10);
		
		tfCim = new JTextField();
		tfCim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCim.setColumns(10);
		tfCim.setBounds(58, 61, 164, 23);
		contentPane.add(tfCim);
		
		tfKategoria = new JTextField();
		tfKategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfKategoria.setColumns(10);
		tfKategoria.setBounds(76, 101, 164, 23);
		contentPane.add(tfKategoria);
		
		tfMegjelenes = new JTextField();
		tfMegjelenes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfMegjelenes.setColumns(10);
		tfMegjelenes.setBounds(80, 139, 164, 23);
		contentPane.add(tfMegjelenes);
		
		tfAr = new JTextField();
		tfAr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfAr.setColumns(10);
		tfAr.setBounds(50, 185, 128, 23);
		contentPane.add(tfAr);
		
		tfVevoID = new JTextField();
		tfVevoID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfVevoID.setColumns(10);
		tfVevoID.setBounds(61, 226, 96, 23);
		contentPane.add(tfVevoID);
		
		JButton btnNewButton = new JButton("Beszúr");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.filled(tfAzonosito, "Azonosito"))
					if(c.filled(tfCim, "cim"))
						if(c.filled(tfKategoria, "kategoria"))
							if(c.goodInt(tfMegjelenes, "megjelenes"))
								if(c.goodInt(tfAr, "ar"))
									if(c.goodInt(tfVevoID, "VevoID"))
				dbm.InsertKonyv(RTF(tfAzonosito), RTF(tfCim), RTF(tfKategoria), RTF(tfMegjelenes), RTF(tfAr), RTF(tfVevoID));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(38, 274, 128, 46);
		contentPane.add(btnNewButton);
		
		JButton btnBezr = new JButton("Bezár");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBezr.setBounds(285, 274, 128, 46);
		contentPane.add(btnBezr);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
