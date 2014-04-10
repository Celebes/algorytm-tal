package pl.edu.wat.tal;

import java.io.File;

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
					
					// rob cos dalej z grafem..
					
					
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
