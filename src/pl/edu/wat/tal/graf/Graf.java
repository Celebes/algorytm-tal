package pl.edu.wat.tal.graf;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	/*
	 * Liczba cyklomatyczna. Gdy rowna sie 0, to graf jest acykliczny. Wzor dla grafu G = (V, E):
	 * cyc(G) = |E| - |V| + 1
	 */
	public int getCyclomaticNumber() {
		return (this.getKrawedzie().size() - this.getWierzcholki().size() + 1);
	}
	
	/*
	 * Wylicza liczbe cyklomatyczna dla grafu pozbawionego wybranego wierzcholka
	 */
	public int getCyclomaticNumberWithoutVertex(int numerWierzcholka) {
		Graf temp = this;
		temp.removeWierzcholek(numerWierzcholka);
		return temp.getCyclomaticNumber();
	}
	
	/*
	 * Usuwa wierzcholek i wszystkie incydentne (majace w nim koniec lub poczatek) krawedzie
	 */
	public void removeWierzcholek(int numer) {
		Iterator<Krawedz> kIterator = krawedzie.iterator();
		
		while(kIterator.hasNext()) {
			Krawedz k = kIterator.next();
			
			if(k.getA().getNumer() == numer || k.getB().getNumer() == numer) {
				kIterator.remove();
			}
		}
		
		Iterator<Wierzcholek> wIterator = wierzcholki.iterator();
		
		while(wIterator.hasNext()) {
			Wierzcholek w = wIterator.next();
			
			if(w.getNumer() == numer) {
				wIterator.remove();
				break;
			}
		}
	}

}
