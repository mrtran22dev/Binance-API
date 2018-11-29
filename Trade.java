package com.binance;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Trade {
	private URL url;
	private int statusCode;
	private String host;
	
	Trade() throws MalformedURLException, NullPointerException {
		url = null;
	}
	
	Trade(LoginSetup l) throws MalformedURLException {
		url = new URL(l.getURL());
	}
	
	public String getKline(LoginSetup l) throws IOException {
		String resource = "/api/v1/klines";
		url = new URL(l.endpoint + resource + l.symbol + "&" + l.limit + "&" + l.interval);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 		// (HttpURLConnection) = casting a URLConnection with support for HTTP-specific features. HttpURLConnection is abstract class and CANNOT be instantiated																		
																				// url.openConnection() returns URLConnection instance	
		conn.setRequestMethod("GET");
		//conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		//conn.setRequestProperty("charset", "utf-8");
	    conn.connect();
	    InputStream is = conn.getInputStream();
	    
	    // scanner.hasNextLine()
	    // String json = new Scanner(is, "UTF-8").useDelimiter("\\Z").next();		// This one line can consolidate the THREE lines below it	
	    Scanner scan = new Scanner(is, "UTF-8");									// Scanner(InputStream source, String charsetName)
	    String json = scan.useDelimiter("\\Z").next();
	    scan.close();
	    conn.disconnect();
	    statusCode = conn.getResponseCode();
	    host = url.getHost();
		return json;
	}
	
	private URL URL(String string) {
		return null;
	}

	public String getAccount(String apiKey, String query, LoginSetup l) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
		String resource = "/api/v3/account";
		url = new URL(l.endpoint + resource + "?" + query);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 			// (HttpURLConnection) = casting a URLConnection with support for HTTP-specific features. HttpURLConnection is abstract class and CANNOT be instantiated																		
																					// url.openConnection() returns URLConnection instance	
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("X-MBX-APIKEY", apiKey);
	    conn.connect();
	    InputStream is = conn.getInputStream();
	    Scanner scan = new Scanner(is, "UTF-8");									// Scanner(InputStream source, String charsetName)
	    String json = scan.useDelimiter("\\Z").next();
	    scan.close();
	    conn.disconnect();
	    statusCode = conn.getResponseCode();
	    host = url.getHost();
		return json;
		
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public String getHost() {
		return host;
	}
}
