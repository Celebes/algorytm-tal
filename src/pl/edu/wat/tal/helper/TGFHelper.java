package pl.edu.wat.tal.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		
		BufferedReader br;
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			String line;
			
			System.out.println("*** START parsowania pliku ***\n");
			
			boolean odczytWierzcholkow = true;
			
			while ((line = br.readLine()) != null) {

			   if(line.equals("#")) {
				   odczytWierzcholkow = false;
				   continue;
			   }

			   if(odczytWierzcholkow) {
				   graf.createWierzcholekFromLine(line);
			   } else {
				   graf.createKrawedzFromLine(line);
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
