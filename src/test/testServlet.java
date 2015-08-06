package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class testServlet {
	//private static String urlPath = "http://localhost:8080/testServlet/UserOperations=register";
	private static String urlPath = "http://localhost:8080/testServlet/Register";
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		StringBuilder json = new StringBuilder();
		
		URL oracle = new URL(testServlet.urlPath); 
	    URLConnection yc = oracle.openConnection(); 
	    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
	    String inputLine = null; 
	    while ( (inputLine = in.readLine()) != null){ 
	      json.append(inputLine); 
	    } 
	    in.close(); 
	    String Strjson=json.toString();
	    System.out.println("原始数据:");
	    System.out.println(Strjson.toString()); 
	}
}
