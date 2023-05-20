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
import java.awt.Color;

public class ArkeresList extends JDialog {
	private JTable table;
	private ArkeresTM artm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ArkeresList(JFrame f,ArkeresTM bartm) {
		super(f,"Könyvek a megadott árak között",true);
		getContentPane().setBackground(new Color(50, 214, 214));
		artm=bartm;
		
		setBounds(100, 100, 645, 366);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(267, 283, 98, 36);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 602, 247);
		getContentPane().add(scrollPane);
		
		table = new JTable(artm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 5; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==4 || i==5 ) tc.setPreferredWidth(30);
		if(i==3) tc.setPreferredWidth(70);
		else {tc.setPreferredWidth(100);}
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();

	}

}
