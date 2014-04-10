package pl.edu.wat.tal.graf;

public class Wierzcholek {
	
	private int numer;
	private String nazwa;

	public Wierzcholek(int numer, String nazwa) {
		this.numer = numer;
		this.nazwa = nazwa;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	@Override
	public String toString() {
		return "[numer: " + numer + " | nazwa: " + nazwa + "]";
	}

}
