package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestResFileReader {

	public static void main(String[] args) throws IOException {
		//File file = new File("com/rumboj/res/rumboj.phones.list");
		InputStream str = ClassLoader.getSystemResourceAsStream("com//rumboj//res//rumboj.phones.list");
		
		InputStreamReader isr = new InputStreamReader(str);
		BufferedReader br = new BufferedReader(isr);
		
		String line = br.readLine();
		
	     while(line != null){
	    	 System.out.println(line);
	    	 line = br.readLine();
	     }
	     isr.close();
	     br.close();
	}

}
