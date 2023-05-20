package konyvesbolt;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.Color;

public class EmpList extends JDialog {
	private JTable table;
	private EmpTM etm;

	public EmpList(JFrame f, EmpTM betm) {
		super(f,"Vevők listája", true);
		getContentPane().setBackground(new Color(50, 214, 214));
		etm=betm;
		
		setBounds(100, 100, 445, 389);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(159, 288, 96, 35);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 27, 380, 240);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);

		TableColumn tc = null;
		for (int i = 0; i < 3; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 ) tc.setPreferredWidth(20);
		else {tc.setPreferredWidth(100);}
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();
//		trs.setSortable(0, false);
		
	}
}
