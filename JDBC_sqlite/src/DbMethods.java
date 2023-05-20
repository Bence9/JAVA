import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbMethods {
	
	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
//			SM("Sikeres driver regisztralas");
		}catch(Exception ex) {
			SM(ex.getMessage());
		}
		
	}
	
	public void SM2(String s){
		System.out.println(s);
	}
	
	public void SM(String msg){
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", 2);
	}
	
	public Connection Connect() {
		Connection conn = null;
		String url = "jdbc:sqlite:C:/sql3/autodb";
		try {
			conn = DriverManager.getConnection(url);
//			SM("Sikeres kapcsolodas");
			return conn;
		}catch(Exception ex) {
			SM(ex.getMessage());
			return conn;
		}
	}
	
	public void DisConnect(Connection conn) {
		if(conn != null);
		try {
			conn.close();
//			SM("Sikeres lekapcsolódás");
		}catch(Exception ex) {
			SM(ex.getMessage());
		}	
	}
	
	public void CommandExec(String command) {
		Connection conn = Connect();
		String sqlP = "PRAGMA foreign_keys=on;";
		String sqlp = command;
		SM("Command:" + sqlp);
		try {
			Statement s = conn.createStatement();
			s.execute(sqlP);
			s.execute(sqlp);
			SM("Command OK");
		}catch(SQLException e) {
			String msg = e.getMessage();
			if(msg.contains("foreign key constraint failed")) msg = " Hibás idegen kulcs érték!";
			SM("CommandExec:" + msg);
		}
		DisConnect(conn);
	}
	
	public void ReadAllData(){
		String rend="", tip="", szin="",x="\t";
		int kor=0, ar=0;
		String sqlp="SELECT rendszam, tipus, szin, kor, ar FROM Auto";
		Connection conn=Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				rend = rs.getString("rendszam");
				tip = rs.getString("tipus");
				szin = rs.getString("Szin");
				kor = rs.getInt("kor");
				ar = rs.getInt("ar");
				SM(rend+x+tip+x+szin+x+kor+x+ar);
			}
			rs.close();
		}catch(SQLException e) {
			SM("ReadAllData:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void Insert(String rsz, String tip, String szin, String kor, String ar) {
		Connection conn= Connect();
		String sqlp = "INSERT INTO Auto Values('"+rsz+"', '"+tip+"', '"+szin+"', "+kor+", "+ar+")";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK");
		}catch(SQLException e) {
			SM("JDBC Insert:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void DeleteData(String rsz) {
		Connection conn = Connect();
		String sqlp= "DELETE FROM Auto WHERE rendszam='"+rsz+"'";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			int db = s.executeUpdate(sqlp);
			if(db==0) SM("A megadott rendszámú autó nem létezik,nem törölhető!");
			else SM("Törlődött a '"+rsz+"' rendszámú autó!");
		}catch(SQLException e) {
			SM("JDBC DeleteData:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void UpdateData(String rsz, String ar) {
		Connection conn = Connect();
		String sqlp = "UPDATE Auto SET ar= "+ar+" WHERE rendszam= '"+rsz+"'";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			int db = s.executeUpdate(sqlp);
			if(db==0) SM("A megadott rendszámú autó nem létezik,nem történt változás!");
			else SM("A megadott rendszámú autó új ára:" +ar);
		}catch(SQLException e) {
			SM("JDBC UpdateData:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void InsertwithPS(Auto[] auto) {
		Connection conn = Connect();
		int db=0;
		String sqlp = "INSERT INTO Auto Values(?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlp);
			for(int i=0; i < auto.length;i++) {
				ps.setString(1, auto[i].getRendszam());
				ps.setString(2, auto[i].getTipus());
				ps.setString(3, auto[i].getSzin());
				ps.setInt(4, auto[i].getKor());
				ps.setInt(5, auto[i].getAr());
				ps.execute();
				db++;
			}
			SM(db + "darab rekord beszúrva");
		}catch(SQLException e) {
			SM(e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void ArLista(String ar1, String ar2) {
		String rend= "", tip="", szin="", x="\t";
		int kor=0, ar=0;
		String sqlp = "SELECT * from Auto WHERE ar >=" +ar1+ " AND ar <=" +ar2+ " ORDER BY ar";
		Connection conn = Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			SM("\n Autók " +ar1+ " és " +ar2+ " közti árral");
			SM("=================================");
			while(rs.next()) {
				rend = rs.getString("rendszam");
				tip = rs.getString("tipus");
				szin = rs.getString("szin");
				kor = rs.getInt(kor);
				ar = rs.getInt(ar);
				SM(rend+x+tip+x+szin+x+kor+x+ar);
			}
		}catch(SQLException e) {
			SM(e.getMessage());
		}
		DisConnect(conn);
	}
	
	public int SelectCount(String table, String condition) {
		int pc = -1;
		String sqlp = "SELECT count(*) From "+table+" where " +condition;
		Connection conn = Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				pc = rs.getInt(1);
			}
			rs.close();
		}catch(SQLException e) {
			SM(e.getMessage());
		}
		DisConnect(conn);
		return pc;
	}
	
	
}
