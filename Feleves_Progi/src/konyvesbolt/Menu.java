package konyvesbolt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnKilep;
	private KonyvTM ktm;
	private JButton btnTáblázat;
	private JButton btnHatter;
	private static Color color = Color.BLUE;
	private static int red=0;
	private static int green=153;
	private static int blue=204;
	private JButton btnKeres;
	private JButton btnStatisztika;
	private JButton btnForrasfajl;
	private JTextField textforras;
	private File sourceFile;
	private String forras = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		BackgroundColorChange();
		setBackground(new Color(red, green, blue));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 345);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sourceFile = new File(System.getProperty("user.dir"));
		forras = sourceFile.getAbsolutePath();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/konyvespolc2.png")));
		lblNewLabel.setBounds(153, 50, 450, 240);
		contentPane.add(lblNewLabel);
		
		btnKilep = new JButton("Kilépés");
		btnKilep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKilep.setActionCommand("Kilepes");
		btnKilep.setBounds(10, 250, 133, 40);
		contentPane.add(btnKilep);
		
		btnTáblázat = new JButton("Táblázat kezelése");
		btnTáblázat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTáblázat.setActionCommand("Táblázat");
		btnTáblázat.setBounds(10, 50, 133, 40);
		contentPane.add(btnTáblázat);
		
		btnHatter = new JButton("Háttérszín");
		btnHatter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHatter.setBounds(10, 200, 133, 40);
		contentPane.add(btnHatter);
		
		btnKeres = new JButton("Keres");
		btnKeres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKeres.setBounds(10, 150, 133, 40);
		contentPane.add(btnKeres);
		
		btnStatisztika = new JButton("Statisztika");
		btnStatisztika.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStatisztika.setBounds(10, 100, 133, 40);
		contentPane.add(btnStatisztika);
		
		btnForrasfajl = new JButton("Fájl kiválasztása");
		btnForrasfajl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnForrasfajl.setActionCommand("forrasfajl");
		btnForrasfajl.setBounds(10, 15, 133, 25);
		contentPane.add(btnForrasfajl);
		
		textforras = new JTextField();
		textforras.setBounds(155, 15, 450, 25);
		textforras.setEditable(false);
		contentPane.add(textforras);
		textforras.setColumns(10);
		textforras.setText(forras);
		
		
		Object kmptmn[] = {"Jel","Kód","Cím","Kategória","Megjelenés","Ár"};
		ktm = new KonyvTM(kmptmn, 0);
		
		this.btnKilep.addActionListener(this);
		this.btnTáblázat.addActionListener(this);
		this.btnHatter.addActionListener(this);
		this.btnKeres.addActionListener(this);
		this.btnStatisztika.addActionListener(this);
		this.btnForrasfajl.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Kilepes")) {
			try {
				PrintStream out = new PrintStream(new FileOutputStream("hatterSzin.txt", false));
				out.println(0+";"+153+";"+204+";");
				out.close();
				
			} catch (IOException ioe) {
				System.out.println("Kiírási hiba!");
			}
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("Táblázat")) {
			ktm = FileManager.CsvReader("konyvek.csv");
			KonyvKezelo km = new KonyvKezelo(Menu.this, ktm);
			km.setVisible(true);
		}
		
		if(e.getActionCommand().equals("Statisztika")) {
			ktm = FileManager.CsvReader("konyvek.csv");
			KonyvStat ks = new KonyvStat(Menu.this);
			ks.setVisible(true);
		}
		
		if(e.getActionCommand().equals("Keres")) {
			ktm = FileManager.CsvReader("konyvek.csv");
			Kereses ke = new Kereses(Menu.this);
			ke.setVisible(true);
		}
		
		if(e.getActionCommand().equals("Háttérszín")) {
			JColorChooser colorchooser = new JColorChooser();
			color = JColorChooser.showDialog(null, "choose a color", Color.WHITE);
			contentPane.setBackground(color);
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			try {
				PrintStream out = new PrintStream(new FileOutputStream("hatterSzin.txt", false));
				out.println(red+";"+green+";"+blue+";");
				out.close();
				
			} catch (IOException ioe) {
				System.out.println("Kiírási hiba!");
			}
		}
		
		if(e.getActionCommand().equals("forrasfajl")) {
			JFileChooser jfc = new JFileChooser(sourceFile);
			int returnValue = jfc.showOpenDialog(Menu.this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				textforras.setText(selectedFile.getAbsolutePath());
			}
			if(returnValue == JFileChooser.CANCEL_OPTION){
				textforras.setText(forras);
			}
		}
		
	}
	
	public static void BackgroundColorChange() {
		try {
			Scanner sc = new Scanner(new FileReader("hatterSzin.txt")).useDelimiter(";");
			red=Integer.valueOf(sc.next());
			green=Integer.valueOf(sc.next());
			blue=Integer.valueOf(sc.next());
			color = new Color(red,green,blue);
		}catch(IOException ioe){
			FileManager.SM("Szín hiba!",0);
		}
		catch(NumberFormatException nfe) {
			FileManager.SM("Szín hiba!",0);
	    }
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
}
