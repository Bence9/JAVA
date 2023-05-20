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

public class TorolVevo extends JFrame {

	private JPanel contentPane;
	private JTextField tfVevoID;
	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param  
	 */
	public TorolVevo(JFrame f) {
		super("Vevő törlése");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 285);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adja meg a törölni kívánt Vevő ID-t:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 39, 271, 48);
		contentPane.add(lblNewLabel);
		
		tfVevoID = new JTextField();
		tfVevoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVevoID.setBounds(286, 49, 184, 26);
		contentPane.add(tfVevoID);
		tfVevoID.setColumns(10);
		
		JButton btnNewButton = new JButton("Törlés");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.goodInt(tfVevoID, "VevoID"))
					dbm.DeleteVevo(RTF(tfVevoID));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(33, 146, 110, 48);
		contentPane.add(btnNewButton);
		
		JButton btnBezr = new JButton("Bezár");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBezr.setBounds(307, 146, 110, 48);
		contentPane.add(btnBezr);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
