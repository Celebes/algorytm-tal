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

	public void createWierzcholekFromLine(String line) {
		String[] liniaPoPodziale = line.split(" ");
		
		Wierzcholek w = new Wierzcholek(Integer.parseInt(liniaPoPodziale[0]), liniaPoPodziale[1]);
		wierzcholki.add(w);
		
		System.out.println("Do grafu dodano wierzcholek: " + w);
	}

	public void createKrawedzFromLine(String line) {
		String[] liniaPoPodziale = line.split(" ");
		int numerWierzcholkaA = Integer.parseInt(liniaPoPodziale[0]);
		int numerWierzcholkaB = Integer.parseInt(liniaPoPodziale[1]);
		
		Krawedz k = new Krawedz(findWierzcholekByNumer(numerWierzcholkaA), findWierzcholekByNumer(numerWierzcholkaB));
		krawedzie.add(k);
		
		System.out.println("Do grafu dodano krawedz: " + k);
	}

}
