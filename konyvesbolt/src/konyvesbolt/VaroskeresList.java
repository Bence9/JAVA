package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class VaroskeresList extends JDialog {
	private JTable table;
	private VaroskeresTM vtm;
	
	
	public VaroskeresList(JFrame f, VaroskeresTM bvtm) {
		super(f,"Vevők a kiválasztott városból",true);
		getContentPane().setBackground(new Color(50, 214, 214));
		vtm=bvtm;
		
		setBounds(100, 100, 329, 404);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(92, 320, 95, 37);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 26, 261, 284);
		getContentPane().add(scrollPane);
		
		table = new JTable(vtm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 2; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 ) tc.setPreferredWidth(20);
		else {tc.setPreferredWidth(100);}
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();
		
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
}
