package com.rumboj.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private static Properties prop;
	static {
		load();
	}

	private static void load(String propertyFilename) {
		try {
			prop = new Properties();
			InputStream in = PropertiesReader.class.getResourceAsStream(propertyFilename);
			prop.load(in);
			// prop.load(stream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getProperty(String propertyName) {
		String propValue = prop.getProperty(propertyName);
		return propValue;
	}

	public static void main(String args[]) {
		System.out.println(PropertiesReader.getProperty("phones"));
	}
}
