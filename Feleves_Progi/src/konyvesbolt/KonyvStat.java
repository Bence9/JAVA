package konyvesbolt;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class KonyvStat extends JDialog implements ActionListener{
	private JButton btnBezar;
	private static int red=0;
	private static int green=153;
	private static int blue=204;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KonyvStat dialog = new KonyvStat(null);
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
	public KonyvStat(Frame f) {
		super(f, "Statisztika", true);
		setBounds(100, 100, 492, 297);
		getContentPane().setLayout(null);
		
		BackgroundColorChange();
		getContentPane().setBackground(new Color(red, green, blue));
		setResizable(false);
		
		btnBezar = new JButton("Bezár");
		btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBezar.setBounds(383, 219, 85, 31);
		getContentPane().add(btnBezar);
		
		JLabel lblNewLabel = new JLabel("Táblázatban lévő adatok:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 30, 196, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDarab = new JLabel("10");
		lblDarab.setHorizontalAlignment(SwingConstants.CENTER);
		lblDarab.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDarab.setBounds(196, 30, 40, 20);
		getContentPane().add(lblDarab);
		
		JLabel lblKnyvektlagra = new JLabel("Könyvek átlagára:");
		lblKnyvektlagra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKnyvektlagra.setBounds(10, 70, 146, 20);
		getContentPane().add(lblKnyvektlagra);
		
		JLabel lblAtlag = new JLabel("5000");
		lblAtlag.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtlag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAtlag.setBounds(138, 70, 85, 20);
		getContentPane().add(lblAtlag);
		
		JLabel lblLegdrgbb = new JLabel("Legdrágább:");
		lblLegdrgbb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLegdrgbb.setBounds(10, 111, 104, 20);
		getContentPane().add(lblLegdrgbb);
		
		JLabel lblLegolcsbb = new JLabel("Legolcsóbb:");
		lblLegolcsbb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLegolcsbb.setBounds(10, 152, 104, 20);
		getContentPane().add(lblLegolcsbb);
		
		JLabel lblMax = new JLabel("5000");
		lblMax.setHorizontalAlignment(SwingConstants.LEFT);
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMax.setBounds(106, 111, 362, 20);
		getContentPane().add(lblMax);
		
		JLabel lblMin = new JLabel("5000");
		lblMin.setHorizontalAlignment(SwingConstants.LEFT);
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMin.setBounds(106, 152, 362, 20);
		getContentPane().add(lblMin);

		btnBezar.addActionListener(this);
		
		
		int db = 0;
	    int sor = 0;
        double atlag =0;
        int min = Integer.MIN_VALUE;
        String mintalálat = null;
        int max = Integer.MAX_VALUE;
        String maxtalálat = null;
        
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

	                        if (elem >= 0 && elem <= 10000000) {
	                            atlag = atlag + elem;
	                            db++;
	                        }

	                        if(min<elem) {
	                        	min = elem;
	                        	mintalálat = találat;
	                        }
	                        
	                        if(max>elem) {
	                        	max = elem;
	                        	maxtalálat = találat;
	                        }
	                        
	                    }
	                    sor++;
	                }
	                
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            }

	lblDarab.setText(db + " db");
	atlag = atlag/db;
	lblAtlag.setText(String.format("%.1f", atlag) + " ft");
	lblMax.setText(mintalálat);
	lblMin.setText(maxtalálat);
	
	JLabel lblLeggyakrabbanElfordulKategria = new JLabel("Leggyakrabban előforduló kategória:");
	lblLeggyakrabbanElfordulKategria.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblLeggyakrabbanElfordulKategria.setBounds(10, 189, 270, 20);
	getContentPane().add(lblLeggyakrabbanElfordulKategria);
	
	JLabel lblKategoriaNev = new JLabel((String) null);
	lblKategoriaNev.setHorizontalAlignment(SwingConstants.LEFT);
	lblKategoriaNev.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblKategoriaNev.setBounds(280, 189, 188, 20);
	getContentPane().add(lblKategoriaNev);
     
	
	String[] kategoriak = new String[50];
	int i = 0;
	try (Scanner scanner = new Scanner(new File("konyvek.csv"))) {
	    while (scanner.hasNextLine()) {
	        if (sor >= 2) {
	            String találat = scanner.nextLine();
	            String[] érték = találat.split(";");
	            kategoriak[i] = érték[2];
	            i++;
	        }
	        sor++;
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	int maxGyakorisag = 0;
	String leggyakoribbKategoria = null;

	for (String kategoria : kategoriak) {
	    if (kategoria == null) {
	        // Ha elérjük a tömb vége, kilépünk a for ciklusból
	        break;
	    }
	    int gyakorisag = 0;
	    for (String masikKategoria : kategoriak) {
	        if (masikKategoria == null) {
	            break;
	        }
	        if (kategoria.equals(masikKategoria)) {
	            gyakorisag++;
	        }
	    }
	    if (gyakorisag > maxGyakorisag) {
	        maxGyakorisag = gyakorisag;
	        leggyakoribbKategoria = kategoria;
	    }
	}
	leggyakoribbKategoria=leggyakoribbKategoria.toUpperCase();
	lblKategoriaNev.setText(leggyakoribbKategoria + " (" + maxGyakorisag +")");
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Bezár")) {
			dispose();
		}
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
