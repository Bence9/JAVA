package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModositKonyv extends JFrame {

	private JPanel contentPane;

	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	private JTextField tfAzonosito;
	private JTextField tfAr;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ModositKonyv(JFrame f) {
		super("Könyv árának módosítása");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 297);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adja meg a módosítani kívánt azonosítót:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 57, 270, 29);
		contentPane.add(lblNewLabel);
		
		tfAzonosito = new JTextField();
		tfAzonosito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAzonosito.setBounds(281, 57, 154, 29);
		contentPane.add(tfAzonosito);
		tfAzonosito.setColumns(10);
		
		JLabel lblAdjaMegA = new JLabel("Adja meg a könyv új árát:");
		lblAdjaMegA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdjaMegA.setBounds(10, 117, 270, 29);
		contentPane.add(lblAdjaMegA);
		
		tfAr = new JTextField();
		tfAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAr.setColumns(10);
		tfAr.setBounds(190, 117, 154, 29);
		contentPane.add(tfAr);
		
		JButton btnNewButton = new JButton("Módosít");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.filled(tfAzonosito, "Azonosito"))
					if(c.goodInt(tfAr, "ar"))
						dbm.UpdateKonyv(RTF(tfAzonosito), RTF(tfAr));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(24, 195, 129, 45);
		contentPane.add(btnNewButton);
		
		JButton btnBezr = new JButton("Bezár");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBezr.setBounds(281, 195, 129, 45);
		contentPane.add(btnBezr);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
