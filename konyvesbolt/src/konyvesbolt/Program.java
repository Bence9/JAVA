package konyvesbolt;

public class Program {

	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();
	
	public static void main(String[] args) {
		dbm.Reg();
//		dbm.DisConnect(dbm.Connect());
		
//		String sqlp = "CREATE TABLE Vevo (VevoID Integer Primary key, "+" Nev Varchar2(40), Varos Varchar2(40))";
//		dbm.CommandExec(sqlp);
		
//		String sqlp1 = "CREATE TABLE Konyv (Azonosito char(6) primary key, Cim char(30), Kategoria char(30),"+
//		"Megjelenes number(4), Ar number(6), VevoID int, Foreign key (VevoID) REFERENCES Vevo(VevoID))";
//		dbm.CommandExec(sqlp1);
		
//		 String sqlp = "INSERT INTO Vevo Values(1,'Albínó János','Budapest')";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Vevo Values(2,'Makkfej Atilla','Miskolc')";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Vevo Values(3,'Kiss Elemér','Debrecen')";
//		 dbm.CommandExec(sqlp);
		
//		 String sqlp = "INSERT INTO Konyv Values('111aaa','Ez a baj','krimi',2016,3500,1)";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Konyv Values('122abb','Berci a róka','ifjúsági',2020,2000,2)";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Konyv Values('123abc','Igen!','ösztönző',2017,1500,3)";
//		 dbm.CommandExec(sqlp);
		
//		 String sqlp = "DROP TABLE Konyv";
//		 dbm.CommandExec(sqlp);
		
//		 dbm.ReadAllKonyv();
		
//		 dbm.ReadAllVevo();
		
//		 dbm.SM("Rekord beszurasa");
//		 String azonosito = cm.ReadData("Kerem az azonosítót:");
//		 String cim = cm.ReadData("Kerem a cimet:");
//		 String kategoria = cm.ReadData("Kerem a kategoriat:");
//		 String megjelenes = cm.ReadData("Kerem a konyv megjelenesenek evet:");
//		 String ar = cm.ReadData("Kerem a könyv arat:");
//		 String VevoID = cm.ReadData("Kerem a VevoID-t:");
//		 dbm.InsertKonyv(azonosito, cim, kategoria, megjelenes, ar, VevoID);
		
//		 dbm.SM("Rekord beszurasa");
//		 String Vevoid = cm.ReadData("Kerem a VevoID-t:");
//		 String nev = cm.ReadData("Kerem a nevet:");
//		 String varos = cm.ReadData("Kerem a varost:");
//		 dbm.InsertVevo(Vevoid, nev, varos);
		
//		 dbm.SM("Rekord törlése VevoID alapján:");
//		 String VevoID = cm.ReadData("Kerem a VevoID-t:");
//		 dbm.DeleteVevo(VevoID);
		
//		 dbm.SM("Rekord törlése azonosító alapján:");
//		 String azonosito = cm.ReadData("Kerem a VevoID-t:");
//		 dbm.DeleteKonyv(azonosito);
		
//		 dbm.SM("Rekord módosítása azonosito alapján:");
//		 String azonosito = cm.ReadData("Kerem az azonositot:");
//		 String ar = cm.ReadData("Kerem a könyv új árát:");
//		 dbm.UpdateKonyv(azonosito,ar);
		
//		 dbm.SM("Rekord módosítása VevoID alapján:");
//		 String VevoID = cm.ReadData("Kerem a VevoID-t:");
//		 String varos = cm.ReadData("Kerem a vevő új városát:");
//		 dbm.UpdateVevo(VevoID,varos);
		
//		 dbm.SM("Könyvek keresése 2 ár között:");
//		 String ar1 = cm.ReadData("Kerem az alsó határt:");
//		 String ar2 = cm.ReadData("Kerem a felső határt:");
//		 dbm.ArLista(ar1,ar2);
		
//		 dbm.SM("Vevők keresése város szerint:");
//		 String varos1 = cm.ReadData("Kerem a varost:");
//		 dbm.VarosKeres(varos1);
		
		
	}

}
