package pl.edu.wat.tal.graf;

/*
 * Dla tego problemu wszystkie krawedzie sa zawsze dwukierunkowe
 */

public class Krawedz {
	
	private Wierzcholek a;
	private Wierzcholek b;
	private int waga;
	
	public Krawedz(Wierzcholek a, Wierzcholek b) {
		this.a = a;
		this.b = b;
		this.waga = 0;	// domyslnie waga = 0, pozniej pewnie sie przypisze losowe wagi
	}

	@Override
	public String toString() {
		return "[KrawêdŸ dwukierunkowa pomiêdzy wierzcho³kiem [" + a.getNumer() + "] i [" + b.getNumer() + "] o wadze [" + waga + "]";
	}

	public Wierzcholek getA() {
		return a;
	}

	public void setA(Wierzcholek a) {
		this.a = a;
	}

	public Wierzcholek getB() {
		return b;
	}

	public void setB(Wierzcholek b) {
		this.b = b;
	}

	public int getWaga() {
		return waga;
	}

	public void setWaga(int waga) {
		this.waga = waga;
	}

}
