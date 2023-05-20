package konyvesbolt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbMethods {
	
	public void SM(String s){
		JOptionPane.showMessageDialog(null, s, "Program üzenet", 1);
	}
	
	public void SM2(String s){
		System.out.println(s);
	}
	
	public void SM3(String s){
		JOptionPane.showMessageDialog(null, s, "Program üzenet", 2);
	}

	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
//			SM("Sikeres driver regisztralas");
		}catch(Exception ex) {
			SM(ex.getMessage());
		}
		
	}
	
	public Connection Connect() {
		Connection conn = null;
		String url = "jdbc:sqlite:C:/sql3/konyvdb";
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
			SM("Sikeres végrehajtás");
		}catch(SQLException e) {
			String msg = e.getMessage();
			if(msg.contains("foreign key constraint failed")) msg = " Hibás idegen kulcs érték!";
			SM("CommandExec:" + msg);
		}
		DisConnect(conn);
	}
	
	public KonyvTM ReadAllKonyv(){
		Object kmptmn[] = {"Azonsító","Cím","Kategória","Megjelenés","Ár","VevoID(FK)"};
		KonyvTM ktm = new KonyvTM(kmptmn, 0);
		String azonosito="", cim="", kategoria="",x="\t";
		int megjelenes=0, ar=0, vevoID=0;
		String sqlp="SELECT azonosito,cim,kategoria,megjelenes,ar,VevoID FROM Konyv";
		Connection conn=Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				azonosito = rs.getString("azonosito");
				cim = rs.getString("cim");
				kategoria = rs.getString("kategoria");
				megjelenes = rs.getInt("megjelenes");
				ar = rs.getInt("ar");
				vevoID = rs.getInt("VevoID");
//				SM2(azonosito+x+cim+x+kategoria+x+megjelenes+x+ar+x+vevoID);
				ktm.addRow(new Object[] {azonosito, cim, kategoria, megjelenes, ar, vevoID});
			}
			rs.close();
		}catch(SQLException e) {
			SM("ReadAllKonyv:" + e.getMessage());
		}
		DisConnect(conn);
		return ktm;
	}
	
	public EmpTM ReadAllVevo(){
		Object emptmn[] = {"VevoID","Név","Város"};
		EmpTM etm = new EmpTM(emptmn,0);
		String nev="", varos="",x="\t";
		int vevoID=0;
		String sqlp="SELECT VevoID,nev,varos FROM Vevo";
		Connection conn=Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				vevoID = rs.getInt("VevoID");
				nev = rs.getString("nev");
				varos = rs.getString("varos");
//				SM2(vevoID+x+nev+x+varos);
				etm.addRow(new Object[] {vevoID, nev, varos});
			}
			rs.close();
		}catch(SQLException e) {
			SM("ReadAllVevo:" + e.getMessage());
		}
		DisConnect(conn);
		return etm;
	}
	
	public void InsertKonyv(String azonosito, String cim, String kategoria, String megjelenes, String ar, String VevoID) {
		Connection conn= Connect();
		String sqlp = "INSERT INTO Konyv Values('"+azonosito+"', '"+cim+"', '"+kategoria+"', "+megjelenes+", "+ar+", "+VevoID+")";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("Sikeres végrehajtás");
		}catch(SQLException e) {
			SM("JDBC Insert:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void InsertVevo(String VevoId, String név, String város) {
		Connection conn= Connect();
		String sqlp = "INSERT INTO Vevo Values("+VevoId+", '"+név+"', '"+város+"')";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("Sikeres végrehajtás");
		}catch(SQLException e) {
			SM("JDBC Insert:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void DeleteVevo(String VevoID) {
		Connection conn = Connect();
		String sqlp= "DELETE FROM Vevo WHERE VevoID='"+VevoID+"'";
		int db=SelectCount("Konyv","VevoID = "+ VevoID);
		if(db>0) {
			SM("A megadott vevő nem törölhető, könyv tartozik hozzá");
		} else {
			try {
				Statement s = conn.createStatement();
				s.execute(sqlp);
//				int db = s.executeUpdate(sqlp);
//				if(db==0) SM("A megadott vevő nem létezik,nem törölhető!");
				SM("Törlődött a '"+VevoID+"' kódú vevő!");
			}catch(SQLException e) {
				SM("JDBC DeleteData:" + e.getMessage());
			}
		}
		DisConnect(conn);
	}
	
	public void DeleteKonyv(String azonosito) {
		Connection conn = Connect();
		String sqlp= "DELETE FROM Konyv WHERE Azonosito='"+azonosito+"'";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
//			int db = s.executeUpdate(sqlp);
//			if(db==0) SM("A megadott azonosítójú könyv nem létezik,nem törölhető!");
			SM("Törlődött a '"+azonosito+"' azonosítóval rendelkező könyv!");
		}catch(SQLException e) {
			SM("JDBC DeleteData:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void UpdateKonyv(String azonosito, String ar) {
		Connection conn = Connect();
		String sqlp = "UPDATE Konyv SET ar= "+ar+" WHERE Azonosito= '"+azonosito+"'";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			int db = s.executeUpdate(sqlp);
			if(db==0) SM("A megadott azonosítójú könyv nem létezik,nem történt változás!");
			else SM("A megadott azonosítójú könyv új ára:" +ar);
		}catch(SQLException e) {
			SM("JDBC UpdateData:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public void UpdateVevo(String VevoID, String varos) {
		Connection conn = Connect();
		String sqlp = "UPDATE Vevo SET varos= '"+varos+"' WHERE VevoID= '"+VevoID+"'";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			int db = s.executeUpdate(sqlp);
			if(db==0) SM("A megadott VevoID nem létezik,nem történt változás!");
			else SM("A megadott VevoID vevő új városa:" +varos);
		}catch(SQLException e) {
			SM("JDBC UpdateData:" + e.getMessage());
		}
		DisConnect(conn);
	}
	
	public ArkeresTM ArLista(String ar1, String ar2) {
		Object artmn[] = {"Azonosító","Cím","Kategória","Megjelenés","Ár","VevoID"};
		ArkeresTM artm = new ArkeresTM(artmn, 0);
		String azonosito= "", cim="", kategoria="", x="\t";
		int megjelenes=0, ar=0, VevoID=0;
		String sqlp = "SELECT * from Konyv WHERE ar >="+ar1+" AND ar <="+ar2+" ORDER BY ar ";
		Connection conn = Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			//SM2("\n Könyvek " +ar1+ " és "+ar2+" közötti árral:");
			//SM2("=================================");
			while(rs.next()) {
				azonosito = rs.getString("Azonosito");
				cim = rs.getString("cim");
				kategoria = rs.getString("kategoria");
				megjelenes = rs.getInt("megjelenes");
				ar = rs.getInt("ar");
				VevoID = rs.getInt("VevoID");
				//SM2(azonosito+x+cim+x+kategoria+x+megjelenes+x+ar+x+VevoID);
				artm.addRow(new Object[] {azonosito ,cim, kategoria, megjelenes, ar, VevoID});
			}
		}catch(SQLException e) {
			SM(e.getMessage());
		}
		DisConnect(conn);
		return artm;
	}
	
	public VaroskeresTM VarosKeres(String varos1) {
		Object varostmn[] = {"VevoID","Név"};
		VaroskeresTM vtm = new VaroskeresTM(varostmn, 0);
		String nev="",varos="",x="\t";
		int VevoID=0;
		String sqlp = "SELECT VevoID,nev from Vevo WHERE varos ='"+varos1+"' ORDER BY VevoID";
		Connection conn = Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				VevoID = rs.getInt("VevoID");
				nev = rs.getString("nev");
				vtm.addRow(new Object[] {VevoID,nev});
			}
		}catch(SQLException e) {
			SM(e.getMessage());
		}
		DisConnect(conn);
		return vtm;
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
