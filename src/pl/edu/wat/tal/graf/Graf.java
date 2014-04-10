package pl.edu.wat.tal.graf;

import java.util.ArrayList;
import java.util.List;

public class Graf {
	private List<Wierzcholek> wierzcholki;
	private List<Krawedz> krawedzie;
	
	public Graf() {
		wierzcholki = new ArrayList<Wierzcholek>();
		krawedzie = new ArrayList<Krawedz>();
	}

	public List<Wierzcholek> getWierzcholki() {
		return wierzcholki;
	}

	public void setWierzcholki(List<Wierzcholek> wierzcholki) {
		this.wierzcholki = wierzcholki;
	}

	public List<Krawedz> getKrawedzie() {
		return krawedzie;
	}

	public void setKrawedzie(List<Krawedz> krawedzie) {
		this.krawedzie = krawedzie;
	}
	
	public Wierzcholek findWierzcholekByNumer(int numer) {
		Wierzcholek result = null;
		
		for(Wierzcholek w : wierzcholki) {
			if(w.getNumer() == numer) {
				result = w;
				break;
			}
		}
		
		return result;
	}

}
