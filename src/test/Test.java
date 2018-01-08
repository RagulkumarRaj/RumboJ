package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import com.google.common.io.Files;

public class Test {
	public static void main(String args[]) throws IOException{
		String text = "Hiiii";
		Files.write(text.getBytes(),new File("C://Data//ReducedPhoneDetails//Test.txt"));
	}
}
