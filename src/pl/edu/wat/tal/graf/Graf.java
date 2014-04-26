package pl.edu.wat.tal.graf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Graf {
	private List<Wierzcholek> wierzcholki;
	private List<Krawedz> krawedzie;
	
	private Set<Set<Wierzcholek>> spojneSkladowe;
	private boolean[] odwiedzone;
	
	private boolean wagowy = false;
	
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
	
	public void addWierzcholek(Wierzcholek w) {
		wierzcholki.add(w);
	}
	
	public void addKrawedz(Krawedz k) {
		krawedzie.add(k);
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
		
		//System.out.println("Do grafu dodano wierzcholek: " + w);
	}

	public void createKrawedzFromLine(String line) {
		String[] liniaPoPodziale = line.split(" ");
		int numerWierzcholkaA = Integer.parseInt(liniaPoPodziale[0]);
		int numerWierzcholkaB = Integer.parseInt(liniaPoPodziale[1]);
		
		Krawedz k = new Krawedz(findWierzcholekByNumer(numerWierzcholkaA), findWierzcholekByNumer(numerWierzcholkaB));
		krawedzie.add(k);
		
		//System.out.println("Do grafu dodano krawedz: " + k);
	}
	
	/*
	 * Liczba cyklomatyczna. Gdy rowna sie 0, to graf jest acykliczny. Wzor dla grafu G = (V, E):
	 * cyc(G) = |E| - |V| + 1
	 */
	public int getCyclomaticNumber() {
		return (this.getKrawedzie().size() - this.getWierzcholki().size() + this.iloscSpojnychSkladowych());
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
	 * Wylicza spadek liczby cyklomatycznej grafu po usunieciu danego wierzcholka
	 */
	public int getCyclomaticNumberDecreaseAfterRemovingVertex(int numerWierzcholka) {
		int przed = this.getCyclomaticNumber();
		
		Graf temp = this.klonuj();
		temp.removeWierzcholek(numerWierzcholka);
		
		int po = temp.getCyclomaticNumber();
		
		return (przed - po);
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
		
		// odswiez wartosc spojnych skladowych!
		znajdzSpojneSkladowe();
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
			
			if(wierzcholek.isPosiadaWage()) {
				temp.setWaga(wierzcholek.getWaga());
			}
			
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
	
	public int iloscSpojnychSkladowych() {
		if(spojneSkladowe == null) {
			return znajdzSpojneSkladowe().size();
		} else {
			return spojneSkladowe.size();
		}
	}
	
	public Set<Set<Wierzcholek>> znajdzSpojneSkladowe() {
		spojneSkladowe = new HashSet<Set<Wierzcholek>>();
		odwiedzone = new boolean[this.wierzcholki.size()];
		
		for(Wierzcholek w : wierzcholki) {
			int indeksAktualnego = wierzcholki.indexOf(w);
			if(odwiedzone[indeksAktualnego] == false) {
				odwiedzone[indeksAktualnego] = true;
				
				Set<Wierzcholek> nowaSpojnaSkladowa = new HashSet<Wierzcholek>();
				
				nowaSpojnaSkladowa.add(w);
				dfs(nowaSpojnaSkladowa, w);
				
				spojneSkladowe.add(nowaSpojnaSkladowa);
			}
		}
		
		return spojneSkladowe;
	}

	private void dfs(Set<Wierzcholek> nowaSpojnaSkladowa, Wierzcholek w) {
		Set<Wierzcholek> sasiedzi = pobierzWszystkichSasiadowWierzcholka(w);
		
		for(Wierzcholek sasiad : sasiedzi) {
			int indeksSasiada = wierzcholki.indexOf(sasiad);
			if(odwiedzone[indeksSasiada] == false) {
				odwiedzone[indeksSasiada] = true;
				
				nowaSpojnaSkladowa.add(sasiad);
				
				dfs(nowaSpojnaSkladowa, sasiad);
			}
		}
	}

	public boolean areAllTrue(boolean[] array) {
	    for(boolean b : array) if(!b) return false;
	    return true;
	}

	public void przyporzadkujLosoweWagi() {
		List<Integer> lista = pickRandom(this.getWierzcholki().size(), this.getKrawedzie().size());
		
		System.out.println(lista);
		
		int i=0;
		
		for(Integer integer : lista) {
			Wierzcholek w = wierzcholki.get(i);
			w.setWaga(lista.get(i) + 1);
			i++;
		}
		
		this.wagowy = true;
	}
	
	public List<Integer> pickRandom(int n, int k) {
		List<Integer> wynik = new ArrayList<Integer>();
	    Random random = new Random();
	    Set<Integer> picked = new HashSet<>();
	    while(picked.size() < n) {
	    	int sizeNaPoczatku = picked.size();
	    	int znaleziona = random.nextInt(k) + 1;
	        picked.add(znaleziona);
	        int sizeNaKoncu = picked.size();
	        if(sizeNaPoczatku != sizeNaKoncu) {
	        	wynik.add(znaleziona);
	        }
	    }
	    return wynik;
	}

	public boolean isWagowy() {
		return wagowy;
	}

	public void setWagowy(boolean wagowy) {
		this.wagowy = wagowy;
	}

}
