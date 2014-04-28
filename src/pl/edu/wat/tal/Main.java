package pl.edu.wat.tal;

import java.io.File;

import pl.edu.wat.tal.algorytmy.AlgorytmBranching;
import pl.edu.wat.tal.algorytmy.AlgorytmBruteForce;
import pl.edu.wat.tal.algorytmy.AlgorytmLayering;
import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.helper.GrafGenerator;
import pl.edu.wat.tal.helper.TGFHelper;

public class Main {
	
	public static final int LICZBA_GENEROWANYCH_GRAFOW = 2;
	public static final int LICZBA_WIERZCHOLKOW_W_GRAFIE = 8;
	public static final int LICZBA_SPOJNYCH_SKLADOWYCH_W_GRAFIE = 3;
	public static final int LICZBA_SERII_POMIAROW_DLA_JEDNEGO_ZADANIA = 1;
	public static final boolean CZY_WAGOWY = true;
	
	public static void main(String[] args) {
		// wczytaj podany w parametrze uruchomieniowym plik
		if(args.length > 0) {
			File file = new File(args[0]);
			
			if(file.isFile()) {
				if(file.getName().endsWith(".tgf")) {
					
					GrafGenerator gg = new GrafGenerator();
					AlgorytmBruteForce abf;
					AlgorytmLayering al;
					
					for(int i=0; i<LICZBA_GENEROWANYCH_GRAFOW; i++) {
						
						Graf g = gg.generujGrafCykliczny(LICZBA_WIERZCHOLKOW_W_GRAFIE, LICZBA_SPOJNYCH_SKLADOWYCH_W_GRAFIE, CZY_WAGOWY);
						
						for(int j=0; j<LICZBA_SERII_POMIAROW_DLA_JEDNEGO_ZADANIA; j++) {
							System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
							System.out.println((j+1) + " SERIA POMIARÓW DLA " + (i+1) + " GRAFU:");
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
							
							abf = new AlgorytmBruteForce(g);
							al = new AlgorytmLayering(g);
							
							abf.compute();
							
							System.out.println("\n-----------------------------------------------------");
							
							al.compute();
							
							System.out.println("\n==========================================================================================================");
						}
						
					}
					
					// jesli wszystko OK to sparsuj plik
					/*TGFHelper tgfHelper = new TGFHelper(file);
					Graf graf = tgfHelper.parseTgfFile();
					graf.przyporzadkujLosoweWagi();
					
					System.out.println(graf);*/
					
					// rob cos dalej z grafem..
					
					
					
					/*AlgorytmBruteForce abf = new AlgorytmBruteForce(graf);
					abf.compute();
					
					System.out.println("\n*****************************************************");
					
					AlgorytmLayering al = new AlgorytmLayering(graf);
					al.compute();*/
					
					// to tylko do testow - uzywac zamiast tego metody compute()!
					//abf.computeZlozonePamieciowo();
					
					// zapisz graf do pliku .tgf
					// tgfHelper.convertGraphToTgf(graf);
					
				} else {
					System.err.println("B³êdne rozszerzenie pliku! Dopuszczalne s¹ tylko pliki .tgf!");
				}
				
			} else {
				System.err.println("Podany plik .tgf nie istnieje!");
			}
			
		} else {
			System.err.println("Musisz podaæ œcie¿kê do pliku .tgf jako parametr!");
		}
		
	}

}
