package pl.edu.wat.tal;

import java.io.File;

import pl.edu.wat.tal.algorytmy.AlgorytmBranching;
import pl.edu.wat.tal.algorytmy.AlgorytmBruteForce;
import pl.edu.wat.tal.algorytmy.AlgorytmLayering;
import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.helper.TGFHelper;

public class Main {
	
	public static void main(String[] args) {
		
		// wczytaj podany w parametrze uruchomieniowym plik
		if(args.length > 0) {
			File file = new File(args[0]);
			
			if(file.isFile()) {
				if(file.getName().endsWith(".tgf")) {
					
					// jesli wszystko OK to sparsuj plik
					TGFHelper tgfHelper = new TGFHelper(file);
					Graf graf = tgfHelper.parseTgfFile();
					graf.przyporzadkujLosoweWagi();
					
					System.out.println(graf);
					
					// rob cos dalej z grafem..
					/*Graf graf = new Graf();
					graf.createWierzcholekFromLine("1 a");
					graf.createWierzcholekFromLine("2 b");
					graf.createWierzcholekFromLine("3 c");
					graf.createWierzcholekFromLine("4 d");
					
					graf.createKrawedzFromLine("1 2");
					graf.createKrawedzFromLine("2 3");
					graf.createKrawedzFromLine("2 4");
					graf.createKrawedzFromLine("3 4");
					
					graf.przyporzadkujLosoweWagi();*/
					
					AlgorytmBruteForce abf = new AlgorytmBruteForce(graf);
					abf.compute();
					
					System.out.println("\n*****************************************************");
					
					AlgorytmLayering al = new AlgorytmLayering(graf);
					al.compute();
					
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
