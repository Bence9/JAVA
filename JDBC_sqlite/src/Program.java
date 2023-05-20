
public class Program {
	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();

	public static void main(String[] args) {
		 dbm.Reg();
//		 dbm.Connect();
//		 dbm.DisConnect(dbm.Connect());
		 
//	     String sqlp = "Create TABLE Auto(Rendszam char(6) primary key," +
//	    		 		"Tipus char(30), Szin char(20),Kor number(3) , Ar number(10), Tulaj int," +
//	    		 		"Foreign key (Tulaj) REFERENCES Tulajdonos(Tkod))";
//	     dbm.CommandExec(sqlp);
		 
//		 String sqlp = "Create TABLE Tulajdonos(Tkod Integer Primary key, "+" Név Varchar2(40),Város Varchar2(40))";
//		 dbm.CommandExec(sqlp);
		 
//		 String sqlp = "INSERT INTO Tulajdonos Values(1, 'Kis Béla', 'Miskolc')";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Tulajdonos Values(2, 'Nagy Géza', 'Eger')";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Tulajdonos Values(3, 'Kő Kata', 'Miskolc')";
//		 dbm.CommandExec(sqlp);
		 
//		 String sqlp ="DROP TABLE Auto";
//		 dbm.CommandExec(sqlp);
		 

//		 String sqlp = "INSERT INTO Auto Values('ABC500', 'Opel Corsa', 'piros', 8, 800000, 1)";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Auto Values('BBM104', 'Suzuki Swift', 'piros', 5, 1500000, 2)";
//		 dbm.CommandExec(sqlp);
//		 sqlp = "INSERT INTO Auto Values('CHR411', 'Renault Twingo', 'piros', 12, 700000, 3)";
//		 dbm.CommandExec(sqlp);
		 
//		 String sqlp = "DELETE FROM Auto WHERE Tulaj=5";
//		 dbm.CommandExec(sqlp);
		 
//		 String sqlp = "INSERT INTO Auto Values('JKL', 'Renault Clio', 'fehér', 7, 500000, 5)";
//		 dbm.CommandExec(sqlp);

//		 dbm.ReadAllData();
		 
//		 dbm.SM("Rekord adatainak beolvasasa, rekord beszurasa");
//		 String rendszam = cm.ReadData("Kerem a rendszamot:");
//		 String tipus = cm.ReadData("Kerem a tipust:");
//		 String szin = cm.ReadData("Kerem a szint:");
//		 String kor = cm.ReadData("Kerem az auto korat:");
//		 String ar = cm.ReadData("Kerem az auto arat:");
//		 String sqlp = "INSERT INTO Auto Values('"+rendszam+"', '"+tipus+"', '"+szin+"', "+kor+", "+ar+")";
//		 dbm.CommandExec(sqlp);
		 
//		 dbm.SM("Rekord adatainak beolvasasa, rekord beszurasa");
//		 String rendszam = cm.ReadData("Kerem a rendszamot:");
//		 String tipus = cm.ReadData("Kerem a tipust:");
//		 String szin = cm.ReadData("Kerem a szint:");
//		 String kor = cm.ReadData("Kerem az auto korat:");
//		 String ar = cm.ReadData("Kerem az auto arat:");
//		 dbm.Insert(rendszam, tipus, szin, kor, ar);
		 
//		 dbm.SM("Rekord törlése rendszám alapján:");
//		 String rsz = cm.ReadData("Kerem a rendszamot:");
//		 String sqlp = "DELETE FROM Auto WHERE rendszam='"+rsz+"'";
//		 dbm.CommandExec(sqlp);
		 
//		 dbm.SM("Rekord törlése rendszám alapján:");
//		 String rsz = cm.ReadData("Kerem a rendszamot:");
//		 dbm.DeleteData(rsz);
		 
//		 dbm.SM("Rekord módosítása rendszám alapján:");
//		 String rsz = cm.ReadData("Kerem a rendszamot:");
//		 String ar = cm.ReadData("Kerem az autó új árát:");
//		 dbm.UpdateData(rsz,ar);
		 
//		while(1 != 0) {
//			menu();
//		}
		
//		Auto[] auto = new Auto[3];
//		auto[0]= new Auto("DER666", "Skoda Rapid", "fehér", 5, 4800000);
//		auto[1]= new Auto("PSW229", "Kia Ceed", "szürke", 3, 6000000);
//		auto[2]= new Auto("QSX417", "Peugeot 3006", "kék", 5, 7000000);
//		dbm.InsertwithPS(auto);
		
//		dbm.SM("Lista ártartomány alapján:");
//		String ar1= cm.ReadData("Kerem a tartomány alsó határát");
//		String ar2= cm.ReadData("Kerem a tartomány felső határát");
//		dbm.ArLista(ar1, ar2);
		 
//		 dbm.SM("Tulajdonos darabszáma Tkód alapján:");
//		 String Tkod = cm.ReadData("Kérem a Tkód értékét:");
//		 int db = dbm.SelectCount("Tulajdonos","Tkod = "+Tkod);
//		 dbm.SM("Eredmény:" +db);
		
//		 dbm.SM("Tulajdonos kódjának módosítása az Autó táblában");
//		 String rsz = cm.ReadData("Kérem az autó rendszámát: ");
//		 int db = dbm.SelectCount("Auto", "Rendszam = '"+rsz+"'");
//		 if(db==0) dbm.SM("Hibás rendszám");
//		 else {
//			 String tkod = cm.ReadData("Kérem a tulajdonos kódjának az értékét:");
//			 db = dbm.SelectCount("Tulajdonos","Tkod = "+tkod);
//			 if(db==0) dbm.SM("Ehhez a kódhoz nem tartozik tulajdonos, így nem adható meg!");
//			 else {
//				 String sqlp ="UPDATE Auto SET Tulaj = "+tkod+" WHERE Rendszam = '"+rsz+"'";
//				 dbm.CommandExec(sqlp);
//			 }
//		 }
		
	}
	
	 static void menu() {
		 System.out.println("\n");
		 System.out.println("menü");
		 System.out.println("=============");
		 System.out.println("0. Kilépés");
		 System.out.println("1. Listázás");
		 System.out.println("2. Beszúrás");
		 System.out.println("3. Törlés");
		 String ms = cm.ReadData("Add meg a választott menü számát:");
		 int m= 0;
		 m =  Integer.parseInt(ms);
		 switch(m){
		 case 0:
			 System.out.println("A program leállt");
			 System.exit(0);
			 break;
		 case 1:
			 System.out.println("Listázás végrehajtása:");
			 dbm.ReadAllData();
			 break;
		 case 2:
			 System.out.println("Beszúrás végrehajtása:");
			 dbm.SM("Rekord adatainak beolvasasa, rekord beszurasa");
			 String rendszam = cm.ReadData("Kérem a rendszámot:");
			 String tipus = cm.ReadData("Kérem a tipust:");
			 String szin = cm.ReadData("Kérem a szint:");
			 String kor = cm.ReadData("Kérem az autó korat:");
			 String ar = cm.ReadData("Kérem az autó árát:");
			 dbm.Insert(rendszam, tipus, szin, kor, ar);
			 break;
		 case 3:
			 System.out.println("Törlés végrehajtása:");
			 dbm.SM("Rekord törlése rendszám alapján:");
			 String rsz = cm.ReadData("Kérem a rendszámot:");
			 dbm.DeleteData(rsz);								//kitörli viszont azt írja ki,hogy nem létezik!
		 }
		 
	 }

}