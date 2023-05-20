package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class Reg extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPassword;
	private Checker c = new Checker();
	
	public Reg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfUser.setBounds(150, 34, 117, 30);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPassword.setBounds(150, 92, 117, 30);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Felhasználónév:");
		lblNewLabel.setBounds(10, 34, 117, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnRegisztral = new JButton("Regisztrálás");
		btnRegisztral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.filled(tfUser,"felhasználónév") && c.filled(tfPassword,"Jelszó")) {
					try {
						FileWriter fw = new FileWriter("Login.txt",true);
						fw.write("\n" + tfUser.getText() + "\t" +tfPassword.getText());
						fw.close();
					}catch(Exception fe) {
						SM("Hiba a file bevitelénél"+fe.getMessage());
					}
					dispose();
				}
			}
		});
		btnRegisztral.setBounds(21, 152, 117, 30);
		btnRegisztral.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnRegisztral);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(189, 152, 117, 30);
		btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnBezar);
		
		JLabel lblJelsz = new JLabel("Jelszó:");
		lblJelsz.setBounds(10, 92, 117, 30);
		lblJelsz.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblJelsz);
	}
	
	public void SM(String msg){
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", 0);
	}
	
}
