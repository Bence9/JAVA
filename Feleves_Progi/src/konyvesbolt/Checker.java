package konyvesbolt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	
	private SimpleDateFormat RDF = new SimpleDateFormat("yyyy.MM.dd");
	
	public boolean filled(JTextField a, String an) {
		String s = RTF(a);
		if(s.length()>0) return true;
		else {
			SM("Hiba: a(z) " +an+ " mező üres!",0);
			return false;
		}
	}
	
	public boolean filled(JTextField a) {
		Checker c = new Checker();
		String s = c.RTF(a);
		if(s.length()>0) return true;
		else return false;
	}
	
	public boolean goodInt(JTextField a, String an) {
		String s = RTF(a);
		boolean b =filled(a,an);
		if(b) try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			SM("Hiba: a(z) " +an+ " mezőben hibás a számadat!",0);
			b=false;
		}
		return b;
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public boolean goodDate(JTextField a, String an) {
		String s = RTF(a);
		boolean b = filled(a,an);
		if(b && DateFormatChecker(s)) return true;
		else {
			SM("A(z) " +an+ " mezőben hibás a dátum",0);
			return false;
		}
	}

	private boolean DateFormatChecker(String s) {
		try {
			Date date = RDF.parse(s);
			if(!RDF.format(date).equals(s)) {return false;}
			return true;
		}catch(ParseException ef) {
			return false;
		}
	}
	
	public int StringToInt(String s) {
		int x=-1;
		try {
			x=Integer.valueOf(s);
		}catch(NumberFormatException e) {
			SM("StringToInt:" + e.getMessage(),0);
		}
		return x;
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
}
