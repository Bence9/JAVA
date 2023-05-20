package konyvesbolt;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Kereses extends JDialog {
	private JTextField tfKod;
	private JTextField tfCim;
	private static int red=0;
	private static int green=153;
	private static int blue=204;
	private Checker c = new Checker();
	private JTextField tfKategoria;
	private JTextField tfAlsoAr;
	private JTextField tfFelsoAr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kereses dialog = new Kereses(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Kereses(Frame f) {
		super(f, "Keresés az adatok között", true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		BackgroundColorChange();
		getContentPane().setBackground(new Color(red, green, blue));
		
		setBounds(100, 100, 483, 363);
		getContentPane().setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Keresés cím alapján:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 72, 157, 41);
		getContentPane().add(lblNewLabel);
		
		JLabel lblKeressKdAlapjn = new JLabel("Keresés kód alapján:");
		lblKeressKdAlapjn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKeressKdAlapjn.setBounds(10, 21, 157, 41);
		getContentPane().add(lblKeressKdAlapjn);
		
		JLabel lblKeressKategriaAlapjn = new JLabel("Keresés kategória alapján:");
		lblKeressKategriaAlapjn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKeressKategriaAlapjn.setBounds(10, 126, 172, 41);
		getContentPane().add(lblKeressKategriaAlapjn);
		
		tfKod = new JTextField();
		tfKod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfKod.setBounds(140, 28, 70, 32);
		getContentPane().add(tfKod);
		tfKod.setColumns(10);
		
		tfCim = new JTextField();
		tfCim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCim.setColumns(10);
		tfCim.setBounds(140, 79, 149, 32);
		getContentPane().add(tfCim);
		
		JButton btnKod = new JButton("Keres");
		btnKod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKod.setBounds(207, 28, 70, 32);
		getContentPane().add(btnKod);
		
		JButton btnCim = new JButton("Keres");
		btnCim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCim.setBounds(283, 79, 70, 32);
		getContentPane().add(btnCim);
		
		JButton btnBezar = new JButton("Bezár");
		btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBezar.setBounds(346, 272, 100, 32);
		getContentPane().add(btnBezar);
		
		tfKategoria = new JTextField();
		tfKategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfKategoria.setColumns(10);
		tfKategoria.setBounds(176, 132, 149, 32);
		getContentPane().add(tfKategoria);
		
		JButton btnKategoria = new JButton("Keres");
		btnKategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKategoria.setBounds(322, 132, 70, 32);
		getContentPane().add(btnKategoria);
		
		JLabel lblAr = new JLabel("Keresés a megadott árak között:");
		lblAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAr.setBounds(10, 191, 212, 41);
		getContentPane().add(lblAr);
		
		tfAlsoAr = new JTextField();
		tfAlsoAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAlsoAr.setColumns(10);
		tfAlsoAr.setBounds(219, 194, 70, 32);
		getContentPane().add(tfAlsoAr);
		
		JLabel lblKeressKategriaAlapjn_1 = new JLabel("-");
		lblKeressKategriaAlapjn_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKeressKategriaAlapjn_1.setBounds(295, 190, 14, 41);
		getContentPane().add(lblKeressKategriaAlapjn_1);
		
		tfFelsoAr = new JTextField();
		tfFelsoAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfFelsoAr.setColumns(10);
		tfFelsoAr.setBounds(308, 194, 70, 32);
		getContentPane().add(tfFelsoAr);
		
		JButton btnAr = new JButton("Keres");
		btnAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAr.setBounds(386, 195, 70, 32);
		getContentPane().add(btnAr);
		btnKod.addActionListener(e -> Kereskód());
		btnCim.addActionListener(e -> Kerescim());
		btnBezar.addActionListener(e -> Bezar());
		btnKategoria.addActionListener(e -> Kategoria());
		btnAr.addActionListener(e -> Ar());
	        
	}
	
	private void Kereskód() {
        String kereskód = tfKod.getText();
        int db = 0;
        if(c.filled(tfKod,"Kód")) {
        try (Scanner scanner = new Scanner(new File("konyvek.csv"))) {
            while (scanner.hasNextLine()) {
                String találat = scanner.nextLine();
                String[] érték = találat.split(";");
                if (érték[0].equals(kereskód)) {
                    JOptionPane.showMessageDialog(this, "Megtalálva: " + találat, "Keresés eredménye", JOptionPane.INFORMATION_MESSAGE);
                    tfKod.setText("");
                    db++;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(db==0) {
        	SM("Nincs találat",0);
            tfKod.setText("");
        }
        }
    }
	
	private void Kerescim() {
        String kerescim = tfCim.getText();
        int db =0;
        if(c.filled(tfCim,"Cím")) {
        try (Scanner scanner = new Scanner(new File("konyvek.csv"))) {
            while (scanner.hasNextLine()) {
                String találat = scanner.nextLine();
                String[] érték = találat.split(";");
                if (érték[1].equals(kerescim)) {
                    JOptionPane.showMessageDialog(this, "Megtalálva: " + találat, "Keresés eredménye", JOptionPane.INFORMATION_MESSAGE);
                    tfCim.setText("");
                    db++;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(db==0) {
        	SM("Nincs találat",0);
            tfCim.setText("");
        }
        }
    }
	
	private void Kategoria() {
        String kategoria = tfKategoria.getText();
        int db =0;
        if(c.filled(tfKategoria,"Kategória")) {
        try (Scanner scanner = new Scanner(new File("konyvek.csv"))) {
            while (scanner.hasNextLine()) {
                String találat = scanner.nextLine();
                String[] érték = találat.split(";");  
                if (érték[2].equals(kategoria)) {
                    JOptionPane.showMessageDialog(this, "Megtalálva: " + találat, "Keresés eredménye", JOptionPane.INFORMATION_MESSAGE);
                    tfKategoria.setText("");
                    db++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(db==0) {
            SM("Nincs találat",0);
            tfKategoria.setText("");
        }
        }
    }
	
	public void Ar() {
	    int AlsoAr = 0;
	    int FelsoAr = 0;
	    try {
	        AlsoAr = Integer.parseInt(tfAlsoAr.getText());
	    } catch (NumberFormatException ex) {
	        SM("Hibás  az 1. érték", 0);
	        tfAlsoAr.setText("");
	        return;
	    }
	    try {
	        FelsoAr = Integer.parseInt(tfFelsoAr.getText());
	    } catch (NumberFormatException ex) {
	        SM("Hibás a 2. érték", 0);
	        tfFelsoAr.setText("");
	        return;
	    }
	    int db = 0;
	    int sor = 0;
	    if (FelsoAr < AlsoAr) {
	        SM("a 1.érték nagyobb a 2.értéknél", 0);
	        tfAlsoAr.setText("");
	        tfFelsoAr.setText("");
	        return;
	    }
	    
        if (FelsoAr == AlsoAr) {
            SM("a 1.érték egyenlő a 2.értékkel", 0);
            tfAlsoAr.setText("");
            tfFelsoAr.setText("");
            return;
        }
        
	            try (Scanner scanner = new Scanner(new File("konyvek.csv"))) {
	                while (scanner.hasNextLine()) {
	                    if (sor >= 2) {
	                        String találat = scanner.nextLine();
	                        String[] érték = találat.split(";");
	                        int elem = 0;
	                        try {
	                            elem = Integer.parseInt(érték[4]);
	                        } catch (NumberFormatException ex) {
	                            // Ha a 4. oszlop eleme nem lenne integer
	                            continue;
	                        }

	                        if (elem >= AlsoAr && elem <= FelsoAr) {
	                            JOptionPane.showMessageDialog(this, "Megtalálva: " + találat, "Keresés eredménye", JOptionPane.INFORMATION_MESSAGE);
	                            tfAlsoAr.setText("");
	                            tfFelsoAr.setText("");
	                            db++;
	                        }

	                    }
	                    sor++;
	                }
	                if (db == 0) {
	                    SM("Nincs találat", 0);
	                    tfAlsoAr.setText("");
	                    tfFelsoAr.setText("");
	                }
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            }

	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	private void Bezar() {
		dispose();
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
