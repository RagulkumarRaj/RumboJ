package com.rumboj.services.downloadService;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class PhantomHtmlPageDownloadService extends AbstractHtmlPageDownloadService{

	public static void main(String args[]) {
		PhantomHtmlPageDownloadService serv = new PhantomHtmlPageDownloadService();
		String page = serv.downloadPageAsString("http://google.com");
		System.out.println(page);
		System.out.println("##################################################################");
		page = serv.downloadPageAsString("http://yahoo.com");
		System.out.println(page);
	}

	@Override
	public synchronized String downloadPageAsString(String url) {
		try {
			modifyScriptFile(url);
			//String cmd[] = {"C:\\PhantomJS\\phantomjs.exe", "--proxy-type","=socks5"," --proxy","=127.0.0.1:9150","C:\\PhantomJS\\script.js"};
			String cmd[] = {"C:\\PhantomJS\\phantomjs.exe","C:\\PhantomJS\\script.js"};
			Process process = Runtime.getRuntime().exec(cmd);
			boolean exitStatus = process.waitFor(20, TimeUnit.SECONDS);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String currentLine = null;
			//StringBuilder stringBuilder = new StringBuilder(
			//		exitStatus? "SUCCESS:" : "ERROR:");
			StringBuilder stringBuilder = new StringBuilder("");
			currentLine = bufferedReader.readLine();
			while (currentLine != null) {
				stringBuilder.append(currentLine);
				stringBuilder.append("\n");
				currentLine = bufferedReader.readLine();
			}
			return stringBuilder.toString();
			//System.out.println(stringBuilder.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	
	}

	@Override
	public String downloadPageWithProxyAsString(String url, String proxy, String port,
			String proxyType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void modifyScriptFile(String url) throws IOException{
		File file = new File("C:\\PhantomJS\\script.js");
		StringBuilder scriptContent = new StringBuilder("");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		//scriptContent.append(line+"\n");
		while(line!=null){
			scriptContent.append(line);
			scriptContent.append("\n");
			line = br.readLine();
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
