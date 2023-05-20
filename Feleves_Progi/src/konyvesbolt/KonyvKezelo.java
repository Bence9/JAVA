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
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class KonyvKezelo extends JDialog implements ActionListener{
	private JTable table;
	private KonyvTM ktm;
	private Checker c = new Checker();
	private JTextField tfKod;
	private JTextField tfCim;
	private JTextField tfKategoria;
	private JTextField tfMegjelenes;
	private JTextField tfAr;
	private JButton btnBeszur;
	private JButton btnModositas;
	private JButton btnTorles;
	private JButton btnBezar;
	private static int red=0;
	private static int green=153;
	private static int blue=204;
	
	
	public KonyvKezelo(JFrame f, KonyvTM bktm) {
		super(f,"Könyvek kezelése",true);
		ktm=bktm;
		
		BackgroundColorChange();
		getContentPane().setBackground(new Color(red, green, blue));
		setResizable(false);
		
		btnBeszur = new JButton("Beszúr");
		btnBeszur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBeszur.setBounds(10, 280, 124, 25);
		getContentPane().add(btnBeszur);
		
		setBounds(100, 100, 535, 355);
		getContentPane().setLayout(null);
		
		btnBezar = new JButton("Bezár");
		btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBezar.setBounds(385, 280, 124, 25);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 495, 220);
		getContentPane().add(scrollPane);
		
		table = new JTable(ktm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0) tc.setPreferredWidth(30);
		else if(i==1) tc.setPreferredWidth(60);
		else {tc.setPreferredWidth(100);}
		}

		table.setAutoCreateRowSorter(true);
		
		btnModositas = new JButton("Módosítás");
		btnModositas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModositas.setBounds(135, 280, 124, 25);
		getContentPane().add(btnModositas);
		
		tfKod = new JTextField();
		tfKod.setHorizontalAlignment(SwingConstants.CENTER);
		tfKod.setBounds(40, 235, 60, 20);
		getContentPane().add(tfKod);
		tfKod.setColumns(10);
		
		tfCim = new JTextField();
		tfCim.setHorizontalAlignment(SwingConstants.CENTER);
		tfCim.setColumns(10);
		tfCim.setBounds(101, 235, 100, 20);
		getContentPane().add(tfCim);
		
		tfKategoria = new JTextField();
		tfKategoria.setHorizontalAlignment(SwingConstants.CENTER);
		tfKategoria.setColumns(10);
		tfKategoria.setBounds(202, 235, 100, 20);
		getContentPane().add(tfKategoria);
		
		tfMegjelenes = new JTextField();
		tfMegjelenes.setHorizontalAlignment(SwingConstants.CENTER);
		tfMegjelenes.setColumns(10);
		tfMegjelenes.setBounds(303, 235, 100, 20);
		getContentPane().add(tfMegjelenes);
		
		tfAr = new JTextField();
		tfAr.setHorizontalAlignment(SwingConstants.CENTER);
		tfAr.setColumns(10);
		tfAr.setBounds(404, 235, 100, 20);
		getContentPane().add(tfAr);
		
		btnTorles = new JButton("Törlés");
		btnTorles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTorles.setBounds(260, 280, 124, 25);
		getContentPane().add(btnTorles);
		
		JLabel lblNewLabel = new JLabel("Kód");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 255, 60, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblCm = new JLabel("Cím");
		lblCm.setHorizontalAlignment(SwingConstants.CENTER);
		lblCm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCm.setBounds(101, 255, 100, 20);
		getContentPane().add(lblCm);
		
		JLabel lblKategria = new JLabel("Kategória");
		lblKategria.setHorizontalAlignment(SwingConstants.CENTER);
		lblKategria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKategria.setBounds(202, 255, 100, 20);
		getContentPane().add(lblKategria);
		
		JLabel lblMegjelens = new JLabel("Megjelenés");
		lblMegjelens.setHorizontalAlignment(SwingConstants.CENTER);
		lblMegjelens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMegjelens.setBounds(303, 255, 100, 20);
		getContentPane().add(lblMegjelens);
		
		JLabel lblr = new JLabel("Ár");
		lblr.setHorizontalAlignment(SwingConstants.CENTER);
		lblr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblr.setBounds(404, 255, 100, 20);
		getContentPane().add(lblr);
		
		TableRowSorter<KonyvTM> trs =
		(TableRowSorter<KonyvTM>)table.getRowSorter();
		trs.setSortable(0, false);
		
		this.btnBezar.addActionListener(this);
		this.btnTorles.addActionListener(this);
		this.btnBeszur.addActionListener(this);
		this.btnModositas.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Bezár")) {
			dispose();
		}
		
		if(e.getActionCommand().equals("Törlés")) {
			int db=0, jel=0, x=0;
			for(x=0; x<ktm.getRowCount(); x++)
				if((Boolean)ktm.getValueAt(x,0)) {db++; jel=x;}
				if(db==0) FileManager.SM("Nincs kijelölve törlendő rekord", 0);
				if(db>1) FileManager.SM("Több rekord van kijelölve, egyszerre csak egy törölhető!", 0);
				if(db==1) {
					ktm.removeRow(jel);
					FileManager.Insert(ktm);
					FileManager.SM("Rekord törölve", 1);
				}
		}
		
		if(e.getActionCommand().equals("Beszúr")) {
			int db=0, jel=0, x=0;
			for(x=0; x<ktm.getRowCount(); x++)
				if((Boolean)ktm.getValueAt(x,0)) {db++; jel=x;}
			
			if(c.goodInt(tfKod, "Kód")) {
				if(c.filled(tfCim, "Cím")) {
					if(c.filled(tfKategoria, "Kategória")) {
						if(c.goodDate(tfMegjelenes, "Megjelenés")) {
							if(c.goodInt(tfAr, "Ár")) {
							FileManager.Insert(RTF(tfKod),RTF(tfCim),RTF(tfKategoria),RTF(tfMegjelenes),RTF(tfAr));
							
					Object row[] = new Object[6];
					row[1] = tfKod.getText();
					row[2] = tfCim.getText();
					row[3] = tfKategoria.getText();
					row[4] = tfMegjelenes.getText();
					row[5] = tfAr.getText();
					ktm.addRow(row);
					reset(jel);

			} else { reset(jel); }
			} else { reset(jel); }
			} else { reset(jel); }
			} else { reset(jel); }
			} else { reset(jel); }
			
		}
		
		if(e.getActionCommand().equals("Módosítás")) {
			int db=0, jel=0, x=0;
			for(x=0; x<ktm.getRowCount(); x++)
				if((Boolean)ktm.getValueAt(x,0)) {db++; jel=x;}
				if(db==0) FileManager.SM("Nincs kijelölve módosítandó rekord", 0);
				if(db>1) FileManager.SM("Több rekord van kijelölve, egyszerre csak egy módosítható!", 0);
				if(db==1) {
					if(modDataPC()>0) {
						boolean ok = true;
						if(c.filled(tfKod)) ok = c.goodInt(tfKod, "Kod");
						if(c.filled(tfAr)) ok = c.goodInt(tfAr, "Ár");
						if(ok) {
							if(c.filled(tfKod)) ktm.setValueAt(c.StringToInt(c.RTF(tfKod)), jel, 1);
							if(c.filled(tfCim)) ktm.setValueAt(c.RTF(tfCim), jel, 2);
							if(c.filled(tfKategoria)) ktm.setValueAt(c.RTF(tfKategoria), jel, 3);
							if(c.filled(tfMegjelenes)) ktm.setValueAt(c.RTF(tfMegjelenes), jel, 4);
							if(c.filled(tfAr)) ktm.setValueAt(c.StringToInt(c.RTF(tfAr)), jel, 5);
							
							FileManager.Insert(ktm);
							reset(jel);
							FileManager.SM("Rekord módosítva", 1);
						}
					} else {
						FileManager.SM("Nincs kitöltve egyetlen módosító adatmező sem", 0);
					}
					
				}
		}
		
	}
	
	public int modDataPC() {
		int pc=0;
		if(c.filled(tfKod)) pc++;
		if(c.filled(tfCim)) pc++;
		if(c.filled(tfKategoria)) pc++;
		if(c.filled(tfMegjelenes)) pc++;
		if(c.filled(tfAr)) pc++;
		return pc;
	}

	public void reset(int i) {
		tfKod.setText("");
		tfCim.setText("");
		tfKategoria.setText("");
		tfMegjelenes.setText("");
		tfAr.setText("");
		ktm.setValueAt(false, i, 0);
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public static void BackgroundColorChange() {
		try {
			Scanner sc = new Scanner(new FileReader("hatterSzin.txt")).useDelimiter(";");
			red=Integer.valueOf(sc.next());
			green=Integer.valueOf(sc.next());
			blue=Integer.valueOf(sc.next());
		}catch(IOException ioe){
			System.out.println("Sz�n hiba!");
		}
	}
	
}
