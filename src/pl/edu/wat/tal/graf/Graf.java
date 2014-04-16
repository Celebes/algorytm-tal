package pl.edu.wat.tal.graf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	
	public Set<Wierzcholek> pobierzWszystkichSasiadowWierzcholka(Wierzcholek w) {
		Set<Wierzcholek> sasiedzi = new HashSet<Wierzcholek>();
		
		for(Krawedz k : krawedzie) {
			if(k.getA().equals(w)) {
				sasiedzi.add(k.getB());
			}
			
			if(k.getB().equals(w)) {
				sasiedzi.add(k.getA());
			}
		}
		
		return sasiedzi;
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
		Graf temp = this.klonuj();
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

	@Override
	public String toString() {
		return "Graf posiada " + this.wierzcholki.size() + " wierzcholkow (" + this.wierzcholki + ") i " + this.krawedzie.size() + " krawedzi (" + this.krawedzie + ")";
	}

	public Graf klonuj() {
		Graf g = new Graf();
		List<Wierzcholek> w = new ArrayList<Wierzcholek>();
		List<Krawedz> k = new ArrayList<Krawedz>();
		
		for(Wierzcholek wierzcholek : wierzcholki) {
			Wierzcholek temp = new Wierzcholek(wierzcholek.getNumer(), wierzcholek.getNazwa());
			w.add(temp);
		}
		
		g.setWierzcholki(w);
		
		for(Krawedz krawedz : krawedzie) {
			Krawedz temp = new Krawedz(g.findWierzcholekByNumer(krawedz.getA().getNumer()), g.findWierzcholekByNumer(krawedz.getB().getNumer()));
			k.add(temp);
		}
		
		g.setKrawedzie(k);
		
		return g;
	}

}
