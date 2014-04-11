package pl.edu.wat.tal.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	public void convertGraphToTgf(Graf graf) {
		String newFileName = file.getName().replace(".tgf", "") + "_converted.tgf";
		File newFile = new File(file.getAbsolutePath().replace(file.getName(), "") + "\\" + newFileName);
		
		try {
			
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			// zapisz wierzcholki
			
			for(Wierzcholek w : graf.getWierzcholki()) {
				bw.write(w.getNumer() + " " + w.getNazwa() + "\n");
			}
			
			bw.write("#\n");
			
			// zapisz krawedzie
			
			for(Krawedz k : graf.getKrawedzie()) {
				bw.write(k.getA().getNumer() + " " + k.getB().getNumer() + "\n");
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Pomyslnie zapisano plik: " + newFile.getAbsolutePath());
		}

	}

}
