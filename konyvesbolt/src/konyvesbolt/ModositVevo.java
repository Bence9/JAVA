package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ModositVevo extends JFrame {

	private JPanel contentPane;
	
	DbMethods dbm = new DbMethods();
	Checker c = new Checker();
	private JTextField tfVevoID;
	private JTextField tfVaros;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ModositVevo(JFrame f) {
		super("Vevő városának módosítása");
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 291);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adja meg a VevoID-t:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 41, 149, 29);
		contentPane.add(lblNewLabel);
		
		tfVevoID = new JTextField();
		tfVevoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVevoID.setBounds(163, 41, 134, 29);
		contentPane.add(tfVevoID);
		tfVevoID.setColumns(10);
		
		JLabel lblAdjaMegA = new JLabel("Adja meg a vevo új városát:");
		lblAdjaMegA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdjaMegA.setBounds(10, 108, 183, 29);
		contentPane.add(lblAdjaMegA);
		
		tfVaros = new JTextField();
		tfVaros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVaros.setColumns(10);
		tfVaros.setBounds(192, 108, 183, 29);
		contentPane.add(tfVaros);
		
		JButton btnNewButton = new JButton("Módosít");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.goodInt(tfVevoID, "VevoID"))
					if(c.filled(tfVaros, "Varos"))
						dbm.UpdateVevo(RTF(tfVevoID), RTF(tfVaros));
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(22, 189, 104, 39);
		contentPane.add(btnNewButton);
		
		JButton btnBezr = new JButton("Bezár");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBezr.setBounds(248, 189, 104, 39);
		contentPane.add(btnBezr);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
