package pl.edu.wat.tal.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Krawedz;
import pl.edu.wat.tal.graf.Wierzcholek;

public class TGFHelper {
	
	private File file;

	public TGFHelper(File file) {
		this.file = file;
	}

	public Graf parseTgfFile() {
		Graf graf = new Graf();
		List<Wierzcholek> wierzcholkiGrafu = graf.getWierzcholki();
		List<Krawedz> krawedzieGrafu = graf.getKrawedzie();
		
		BufferedReader br;
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			String line;
			
			System.out.println("*** START parsowania pliku ***\n");
			
			boolean odczytWierzcholkow = true;
			String[] liniaPoPodziale = null;
			
			while ((line = br.readLine()) != null) {

			   if(line.equals("#")) {
				   odczytWierzcholkow = false;
				   continue;
			   }

			   if(odczytWierzcholkow) {
				   liniaPoPodziale = line.split(" ");
				   // 0 - numer, 1 - nazwa
				   Wierzcholek w = new Wierzcholek(Integer.parseInt(liniaPoPodziale[0]), liniaPoPodziale[1]);
				   wierzcholkiGrafu.add(w);
				   System.out.println("Do grafu dodano wierzcholek: " + w);
			   } else {
				   liniaPoPodziale = line.split(" ");
				   // 0 - wierzcholek a, 1 - wierzcholek b, 2 - waga krawedzi
				   int numerWierzcholkaA = Integer.parseInt(liniaPoPodziale[0]);
				   int numerWierzcholkaB = Integer.parseInt(liniaPoPodziale[1]);
				   
				   Krawedz k = new Krawedz(graf.findWierzcholekByNumer(numerWierzcholkaA), graf.findWierzcholekByNumer(numerWierzcholkaB));
				   krawedzieGrafu.add(k);
				   System.out.println("Do grafu dodano krawedz: " + k);
			   }
			}
			
			System.out.println("\n*** KONIEC parsowania pliku ***");
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return graf;
	}

}
