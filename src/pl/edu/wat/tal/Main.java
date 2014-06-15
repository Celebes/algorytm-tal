package pl.edu.wat.tal;

import java.io.File;
import java.util.ArrayList;

import pl.edu.wat.tal.algorytmy.AlgorytmBranching;
import pl.edu.wat.tal.algorytmy.AlgorytmBruteForce;
import pl.edu.wat.tal.algorytmy.AlgorytmLayering;
import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.helper.CommonVariables;
import pl.edu.wat.tal.helper.GrafGenerator;
import pl.edu.wat.tal.helper.StatisticsAlgorithmsHelper;
import pl.edu.wat.tal.helper.TGFHelper;

public class Main {
	
	public static void main(String[] args) {
		// wczytaj podany w parametrze uruchomieniowym plik
		if(args.length > 0) {
			File file = new File(args[0]);
			
			if(file.isFile()) {
				if(file.getName().endsWith(".tgf")) {
					
					GrafGenerator gg = new GrafGenerator();
					AlgorytmBruteForce abf;
					AlgorytmLayering al;
					StatisticsAlgorithmsHelper stAlgorithmsHelperForBF;
					StatisticsAlgorithmsHelper stAlgorithmsHelperForLayer;
					ArrayList<ArrayList<Long>> bfAlgorithmResults = new ArrayList<ArrayList<Long>>(CommonVariables.LICZBA_GENEROWANYCH_GRAFOW_W_SERII);
					ArrayList<ArrayList<Long>> layerAlgorithmResults = new ArrayList<ArrayList<Long>>(CommonVariables.LICZBA_GENEROWANYCH_GRAFOW_W_SERII);
					

					//inicjalizacja elementów listy w zale¿noœci od liczby zadañ
					for(int i=0; i<CommonVariables.LICZBA_GENEROWANYCH_GRAFOW_W_SERII; i++)
					{
						bfAlgorithmResults.add(new ArrayList<Long>());
						layerAlgorithmResults.add(new ArrayList<Long>());
					}
					
					int iloscSerii = CommonVariables.SERIA_POMIAROW_DO - CommonVariables.SERIA_POMIAROW_OD + 1;
					
					for(int i=0; i<iloscSerii; i++) {
						int aktualnaSeria = CommonVariables.SERIA_POMIAROW_OD + i;
						System.out.println("Seria pomiarów numer [" + (i+1) + "], rozmiar problemu [" + aktualnaSeria + "]");
						
						for(int j=0; j<CommonVariables.LICZBA_GENEROWANYCH_GRAFOW_W_SERII; j++) {
							Graf g = gg.generujGrafCykliczny(aktualnaSeria, CommonVariables.LICZBA_SPOJNYCH_SKLADOWYCH_W_GRAFIE, CommonVariables.CZY_WAGOWY, CommonVariables.WAGI_ROWNE_JEDEN);
							System.out.println("	Wygenerowano graf numer [" + (j+1) + "]");
							
							for(int k=0; k<CommonVariables.LICZBA_SERII_POMIAROW_DLA_JEDNEGO_ZADANIA; k++) {
								System.out.println("		Przeprowadzono pomiar numer [" + (k+1) + "]");
								
								abf = new AlgorytmBruteForce(g);
								al = new AlgorytmLayering(g);
								stAlgorithmsHelperForBF = new StatisticsAlgorithmsHelper(CommonVariables.ALGORITHM_BRUTE_FORCE);
								stAlgorithmsHelperForLayer = new StatisticsAlgorithmsHelper(CommonVariables.ALGORITHM_LAYERING);
								
								stAlgorithmsHelperForBF.startCalculateComplexity();
				                long bytesStart = Runtime.getRuntime().freeMemory();          
								abf.compute();
								long bytesStop = Runtime.getRuntime().freeMemory();
								//System.out.println("pamiec: " + (bytesStart - bytesStop));
								stAlgorithmsHelperForBF.stopCalculateComplexity();
								bfAlgorithmResults.get(j).add(stAlgorithmsHelperForBF.showResult());
								
								System.out.println("\n-----------------------------------------------------");
								
								stAlgorithmsHelperForLayer.startCalculateComplexity();
								long bytesStart2 = Runtime.getRuntime().freeMemory();   
								al.compute();
								long bytesStop2 = Runtime.getRuntime().freeMemory();
								//System.out.println("pamiec2: " + (bytesStart2 - bytesStop2));
								stAlgorithmsHelperForLayer.stopCalculateComplexity();
								layerAlgorithmResults.get(j).add(stAlgorithmsHelperForLayer.showResult());
								
								System.out.println("\n==========================================================================================================");
							}
							
							System.out.println("\n-----------------------------------------------------");
							System.out.println("Z£O¯NOŒÆ ALGORYTMU BRUTE FORCE DLA " + (j + 1) + " ZADANIA: ");
							int y = 1;
							for(Long result : bfAlgorithmResults.get(j))
							{
								System.out.println("URUCHOMIENIE " + y + ": " + result);
								y++;
							}
							System.out.println("\n-----------------------------------------------------");
							System.out.println("Z£O¯ONOŒÆ ALGORYTMU WARSTWOWEGO DLA " + (j + 1) + " ZADANIA: ");
							int z = 1;
							for(Long result : layerAlgorithmResults.get(j))
							{
								System.out.println("URUCHOMIENIE " + z + ": " + result);
								z++;
							}
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
