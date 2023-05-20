package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KeresVevo extends JFrame {

	private JPanel contentPane;

	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	private JTextField tfVaros;
	private VaroskeresTM vtm;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public KeresVevo(JFrame f) {
		super("Vevők keresése város alapján");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adja meg a várost,ahol a vevőket keresi:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 25, 302, 58);
		contentPane.add(lblNewLabel);
		
		tfVaros = new JTextField();
		tfVaros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVaros.setBounds(10, 93, 302, 30);
		contentPane.add(tfVaros);
		tfVaros.setColumns(10);
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(208, 169, 104, 49);
		contentPane.add(btnNewButton);
		
		JButton btnKeres = new JButton("Keres");
		btnKeres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.filled(tfVaros, "Varos")) {
				vtm=dbm.VarosKeres(RTF(tfVaros));
				VaroskeresList el = new VaroskeresList(KeresVevo.this, vtm);
				el.setVisible(true);
				}
			}
		});
		btnKeres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnKeres.setBounds(22, 169, 104, 49);
		contentPane.add(btnKeres);
		
		Object varostmn[] = {"VevoID","Név"};
		vtm = new VaroskeresTM(varostmn, 0);
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
