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
			SM("Hiba a(z) " +an+ " mező üres");
			return false;
		}
	}
	
	public boolean goodInt(JTextField a, String an) {
		String s = RTF(a);
		boolean b = filled(a,an);
		if(b) try {
			Integer.parseInt(s);
		}catch(NumberFormatException e) {
			SM("A(z) " +an+ " mezőben hibás a számadat");
			b=false;
		}
		return b;
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void SM(String msg){
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", 0);
	}
	
	public boolean goodDate(JTextField a, String an) {
		String s = RTF(a);
		boolean b = filled(a,an);
		if(b && DateFormatChecker(s)) return true;
		else {
			SM("A(z) " +an+ " mezőben hibás a dátum");
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
	
	
}
