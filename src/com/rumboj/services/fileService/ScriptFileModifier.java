package com.rumboj.services.fileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScriptFileModifier {
    
	public void modifyUrl(String url) throws IOException{
		File file = new File("C:\\PhantomJS\\script.js");
		StringBuilder scriptContent = new StringBuilder("");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		//scriptContent.append(line+"\n");
		while(line!=null){
			scriptContent.append(line+"\n");
			line = br.readLine();
			//scriptContent.append(line);
			//scriptContent.append("\n");
		}
		br.close();
        
		int sIndex = scriptContent.indexOf("http");
		int eIndex = scriptContent.indexOf(", function()");
		scriptContent.replace(sIndex, eIndex-1, url);
		
		FileWriter fw = new FileWriter(file);
		fw.write(scriptContent.toString());
		fw.close();
	
    }
}
