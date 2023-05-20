package konyvesbolt;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FileManager {
	

	public static KonyvTM CsvReader(String inputfile) {
		Object emptmn[] = {"Jel","Kód","Cím","Kategória","Megjelenés","Ár"};
		KonyvTM ktm = new KonyvTM(emptmn, 0);
		String x=" - ";
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputfile));
			String s =in.readLine();
			s =in.readLine();
			while(s!=null) {
				String st[]=s.split(";");
				ktm.addRow(new Object[] {false, st[0],st[1],st[2],st[3],st[4]});	
				s=in.readLine();
			}
			in.close();
		}catch (IOException e) {
			System.out.println("CsvReader: " + e.getMessage());
		}
		return ktm;
	}
	
	public static void Insert(String kod, String cim, String kategória, String megjelenés,String ár) {
		String x = ";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("konyvek.csv",true));
			out.println(kod+x+cim+x+kategória+x+megjelenés+x+ár);
			out.close();
			SM("Adatok Beszúrva",1);
		}catch(IOException e) {
			SM("FM.Insert:" + e.getMessage(), 0);
		}
		
	}
	
	public static void Insert(KonyvTM ktm) {
		String x = ";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("konyvek.csv"));
			out.println("Kód;Cím;Kategória;Megjelenés;Ár");
			for(int i=0; i < ktm.getRowCount(); i++) {
				String kod = ktm.getValueAt(i, 1).toString();
				String cim = ktm.getValueAt(i, 2).toString();
				String kat = ktm.getValueAt(i, 3).toString();
				String meg = ktm.getValueAt(i, 4).toString();
				String ar = ktm.getValueAt(i, 5).toString();
				out.println(kod+x+cim+x+kat+x+meg+x+ar);
			}
			out.close();
		}catch(IOException e) {
			SM("FM.Insert:" + e.getMessage(), 0);
		}
		
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}

	
}
