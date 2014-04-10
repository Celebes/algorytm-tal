package pl.edu.wat.tal.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pl.edu.wat.tal.graf.Graf;

public class TGFHelper {
	
	private File file;

	public TGFHelper(File file) {
		this.file = file;
	}

	public Graf parseTgfFile() {
		Graf graf = null;
		
		BufferedReader br;
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			String line;
			
			System.out.println("*** START parsowania pliku ***\n");
			
			while ((line = br.readLine()) != null) {
			   System.out.println(line);
			   // tutaj odczytujemy linie, tworzymy wierzcholki, krawedzie, wszystko pakujemy do grafu
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
