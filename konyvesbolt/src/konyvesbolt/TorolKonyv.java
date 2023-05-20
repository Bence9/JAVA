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

public class TorolKonyv extends JFrame {

	private JPanel contentPane;
	private JTextField tfAzonosito;
	DbMethods dbm = new DbMethods();
	Checker c = new Checker();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TorolKonyv(JFrame f) {
		super("Könyv törlése");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 251);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adja meg a törölni kívánt könyv azonosítóját:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 24, 341, 47);
		contentPane.add(lblNewLabel);
		
		tfAzonosito = new JTextField();
		tfAzonosito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfAzonosito.setBounds(20, 72, 341, 35);
		contentPane.add(tfAzonosito);
		tfAzonosito.setColumns(10);
		
		JButton btnNewButton = new JButton("Töröl");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.filled(tfAzonosito, "Azonosito"))
					dbm.DeleteKonyv(RTF(tfAzonosito));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(23, 141, 105, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Bezár");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(256, 141, 105, 35);
		contentPane.add(btnNewButton_1);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
