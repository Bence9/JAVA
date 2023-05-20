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

public class KonyvList extends JDialog {
	private JTable table;
	private KonyvTM ktm;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public KonyvList(JFrame f, KonyvTM bktm) {
		super(f,"Könyvek listája", true);
		getContentPane().setBackground(new Color(50, 214, 214));
		ktm=bktm;
		setBounds(100, 100, 622, 369);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(254, 281, 94, 34);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 19, 564, 255);
		getContentPane().add(scrollPane);
		
		table = new JTable(ktm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==5 || i==6) tc.setPreferredWidth(70);
		else {tc.setPreferredWidth(100);}
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();
//		trs.setSortable(0, false);

	}
}
