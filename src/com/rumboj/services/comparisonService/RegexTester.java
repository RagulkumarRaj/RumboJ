package com.rumboj.services.comparisonService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
	public static void main(String args[]) {
       String token = new String("1234.45d");
       

		Pattern regex = Pattern.compile("^(\\d+(?:\\.\\d+)?)");
		 Matcher matcher = regex.matcher(token);
		 while(matcher.find()){
			 String num = matcher.group(1);
		     System.out.println(num);
		}
        	
       String token1 = new String("abcd1234.45d");
       
       regex = Pattern.compile("^(\\d+(?:\\.\\d+)?)");
		matcher = regex.matcher(token1);
		 while(matcher.find()){
			 String num = matcher.group(1);
		     System.out.println(num);
		}
	}
}
