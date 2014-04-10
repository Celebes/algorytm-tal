package pl.edu.wat.tal.graf;

import pl.edu.wat.tal.enumy.Kierunek;

public class Krawedz {
	
	private Wierzcholek a;
	private Wierzcholek b;
	private Kierunek kierunek;
	private int waga;
	
	public Krawedz(Wierzcholek a, Wierzcholek b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "[a: " + a.getNumer() + " | b: " + b.getNumer() + "]";
	}

}
