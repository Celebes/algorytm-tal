package pl.edu.wat.tal.algorytmy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;

public class AlgorytmLayeringTest {

	private Graf graf;
	private Set<Wierzcholek> fvs;

	private double minSumaWag = 0;

	public AlgorytmLayeringTest(Graf graf) {
		this.graf = graf;
	}

	public void compute() {
		// utworz zbior wierzcholkow ktory bedzie przechowywal FVS
        fvs = new HashSet<Wierzcholek>();

        // utworz kopie grafu
        Graf grafH = this.graf.klonuj();
        
        // pobierz FVS
        List<Wierzcholek> fvsTEMP = LR_FVS(grafH);
        
        // wynik
        System.out.println("WYNIK");
        System.out.println(fvsTEMP);
	}
	
	public List<Wierzcholek> LR_FVS_MOD(Graf g) {
		List<Wierzcholek> F = new ArrayList<>();
		
		clean(g);
		
		if(g.getWierzcholki().isEmpty()) {
			return F;		// pusty
		}
		
		if(g.zawieraSemiDisjointCycle()) {
			// znajdz wierzcholek o deg(v) > 2
			Wierzcholek uC = null;
			for(Wierzcholek w : g.getWierzcholki()) {
				if(g.obliczStopienWierzcholka(w) > 2) {
					uC = w;
					break;
				}
			}
			
			// wez min wage
			double minWaga = Double.MAX_VALUE;
			int numerWierzcholkaZMinWaga = -1;
			
			for(Wierzcholek w : g.getWierzcholki()) {
				if(w.getWaga() < minWaga) {
					minWaga = w.getWaga();
					numerWierzcholkaZMinWaga = w.getNumer();
				}
			}
			
			
		} else {
			
		}
		
		return F;
	}
	
	public List<Wierzcholek> LR_FVS(Graf g) {
		List<Wierzcholek> F = new ArrayList<>();
		
		// usun wierzcholki o deg < 2
        clean(g);
        
		if(g.getWierzcholki().isEmpty()) {
			return F;		// pusty
		}
		
		double alpha = obliczC(g);
		
		obliczNoweWagiDlaPodgrafu(g, alpha);
		
		List<Wierzcholek> FDASZEK = new ArrayList<>();
		
		for (Wierzcholek w : g.getWierzcholki()) {
			if (Math.abs(w.getWaga()) < 0.0001) {
				FDASZEK.add(w);
			}
		}
		
		List<Wierzcholek> FDWA = new ArrayList<>();
		
		Graf g2 = g.klonuj();
		
		for(Wierzcholek w : FDASZEK) {
			g2.removeWierzcholek(w.getNumer());
		}
		
		FDWA.addAll(LR_FVS(g2));
		
		System.out.println("FDASZEK");
		System.out.println(FDASZEK);
		
		System.out.println("FDWA");
		System.out.println(FDWA);
		
		F.addAll(FDWA);
		
		for(Wierzcholek w : FDASZEK) {
			if(!F.contains(w)) {
				F.add(w);
			}
		}
		
		return F;
	}
	
	/*
	 * Usuwa wierzcholki o deg < 2
	 */
	private void clean(Graf g) {
		List<Wierzcholek> asd = new ArrayList<>();
		for(Wierzcholek w : g.getWierzcholki()) {
			if(g.obliczStopienWierzcholka(w) < 2) {
				asd.add(w);
			}
		}
		
		for(Wierzcholek w : asd) {
			g.removeWierzcholek(w.getNumer());
		}
	}

	public static void main(String[] args) {
		Graf g = new Graf();
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");

		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 1");

		//g.przyporzadkujWagiJeden();
		g.przyporzadkujLosoweWagi();

		AlgorytmLayeringTest alt = new AlgorytmLayeringTest(g);
		
		alt.compute();
	}

	private void usunZeroweWagi(Graf grafH) {
		List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>();

		for (Wierzcholek w : grafH.getWierzcholki()) {
			if (Math.abs(w.getWaga()) < 0.0001) {
				listaWierzcholkowDoUsuniecia.add(w);
				fvs.add(w);
			}
		}

		for (Wierzcholek w : listaWierzcholkowDoUsuniecia) {
			grafH.removeWierzcholek(w.getNumer());
		}
	}

	public void obliczNoweWagiDlaPodgrafu(Graf grafH, double c) {

		for (Wierzcholek w : grafH.getWierzcholki()) {
			double nowaWaga = 0.0;

			// oblicz t = c * spadek_cyklomatycznej_po_usunieciu_v, czyli
			// 'largest cyclomatic weight function in w'
			double t = c
					* (grafH.obliczStopienWierzcholka(w) - 1);
			nowaWaga = w.getWaga() - t;

			w.setWaga(nowaWaga);
		}
	}

	public double obliczC(Graf grafH) {
		boolean pierwszyRaz = true;
		double min = -1;

		for (Wierzcholek w : grafH.getWierzcholki()) {

			double wartoscDlaWierzcholka = (w.getWaga() /  (grafH.obliczStopienWierzcholka(w) - 1));

			if (pierwszyRaz) {
				min = wartoscDlaWierzcholka;
				pierwszyRaz = false;
			} else {
				if (wartoscDlaWierzcholka < min) {
					min = wartoscDlaWierzcholka;
				}
			}
		}

		return min;
	}

	public void usunWierzcholkiKtoreNieZmieniajaLiczbyCyklomatycznej(Graf grafH) {
		List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>();

		for (Wierzcholek w : grafH.getWierzcholki()) {
			if (grafH.getCyclomaticNumberDecreaseAfterRemovingVertex(w
					.getNumer()) == 0) {
				listaWierzcholkowDoUsuniecia.add(w);
			}
		}

		for (Wierzcholek w : listaWierzcholkowDoUsuniecia) {
			grafH.removeWierzcholek(w.getNumer());
		}
	}

}
