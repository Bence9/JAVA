package konyvesbolt;

import java.awt.Color;
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
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class KonyvList extends JDialog {
	private JTable table;
	private KonyvTM ktm;
	private static int red=0;
	private static int green=153;
	private static int blue=204;
	
	public KonyvList(JFrame f, KonyvTM bktm) {
		super(f,"Könyvek listája",true);
		ktm=bktm;
		
		BackgroundColorChange();
		getContentPane().setBackground(new Color(red, green, blue));
		
		setBounds(100, 100, 497, 334);
		getContentPane().setLayout(null);
		setResizable(false);
		
		JButton btnNewButton = new JButton("Bezár");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(175, 262, 95, 25);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 459, 245);
		getContentPane().add(scrollPane);
		
		table = new JTable(ktm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==1) tc.setPreferredWidth(30);
		else if(i==1) tc.setPreferredWidth(80);
		else {tc.setPreferredWidth(100);}
		}

		table.setAutoCreateRowSorter(true);
		TableRowSorter<KonyvTM> trs =
		(TableRowSorter<KonyvTM>)table.getRowSorter();
		trs.setSortable(0, false);
		
	}
	
	public static void BackgroundColorChange() {
		try {
			Scanner sc = new Scanner(new FileReader("hatterSzin.txt")).useDelimiter(";");
			red=Integer.valueOf(sc.next());
			green=Integer.valueOf(sc.next());
			blue=Integer.valueOf(sc.next());
		}catch(IOException ioe){
			System.out.println("Szín hiba!");
		}
	}

}
