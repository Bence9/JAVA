
public class Auto {
	String rendszam;
	String tipus;
	String szin;
	int kor;
	int ar;
	
	public Auto(String rendszam, String tipus, String szin, int kor, int ar) {
		super();
		this.rendszam = rendszam;
		this.tipus = tipus;
		this.szin = szin;
		this.kor = kor;
		this.ar = ar;
	}

	public String getRendszam() {
		return rendszam;
	}

	public String getTipus() {
		return tipus;
	}

	public String getSzin() {
		return szin;
	}

	public int getKor() {
		return kor;
	}

	public int getAr() {
		return ar;
	}

	
}
