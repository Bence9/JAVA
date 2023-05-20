package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField txt_felhasznalo;
	private JPasswordField passwordField_jelszo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bejelentkezés");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(202, 10, 211, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFelhasznlnv = new JLabel("Felhasználónév:");
		lblFelhasznlnv.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblFelhasznlnv.setBounds(10, 89, 195, 32);
		frame.getContentPane().add(lblFelhasznlnv);
		
		JLabel lblNewLabel_1_1 = new JLabel("Jelszó:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1_1.setBounds(10, 153, 195, 32);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txt_felhasznalo = new JTextField();
		txt_felhasznalo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_felhasznalo.setBounds(228, 89, 232, 32);
		frame.getContentPane().add(txt_felhasznalo);
		txt_felhasznalo.setColumns(10);
		
		JButton btnNewButton = new JButton("Bejelentkezés");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String felhasznalonev = txt_felhasznalo.getText();
				String jelszo = passwordField_jelszo.getText();
				
				if(felhasznalonev.contains("bence") && jelszo.contains("12345")) {
					txt_felhasznalo.setText(null);
					passwordField_jelszo.setText(null);
					
					//menu meghívása
					Menu menu = new Menu();
					menu.setVisible(true);
				}
				//null, s, "Program üzenet", 1
				//null, "Hibás jelszó"
				else {
					String s = "Hibás felhasználó-jelszó páros";
					JOptionPane.showMessageDialog(null, s, "Hiba a bejelentkezésnél", 2);
					txt_felhasznalo.setText(null);
					passwordField_jelszo.setText(null);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(34, 241, 130, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnTrl = new JButton("Visszaállít");
		btnTrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_felhasznalo.setText(null);
				passwordField_jelszo.setText(null);
			}
		});
		btnTrl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTrl.setBounds(228, 241, 130, 39);
		frame.getContentPane().add(btnTrl);
		
		JButton btnKilp = new JButton("Kilép");
		btnKilp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKilp.setBounds(425, 241, 130, 39);
		frame.getContentPane().add(btnKilp);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 217, 521, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 56, 521, 2);
		frame.getContentPane().add(separator_1);
		
		passwordField_jelszo = new JPasswordField();
		passwordField_jelszo.setBounds(228, 153, 232, 32);
		frame.getContentPane().add(passwordField_jelszo);
	}
}
