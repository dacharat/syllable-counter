package syllableCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
	private final static String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
	public void readWord() {
		SimpleSyllableCounter counter = new SimpleSyllableCounter();
		int words = 0;
		int sum = 0;
		try{
			URL url = new URL(DICT_URL);
			InputStream input = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			while(true){
				String word = reader.readLine();
				words++;
				if(word == null) break;
				sum += counter.countSyllables(word);
			}
		}catch(IOException e){
			e.getMessage();
		}
		System.out.println("Reading word from dictionary");
		System.out.println("Word : "+words + " Count :"+ sum);
			
	}
	public static void main(String[] args) {
		Main m = new Main();
		m.readWord();
	}
}
