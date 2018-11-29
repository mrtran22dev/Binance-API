package com.binance;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class LoginSetup {
	public String endpoint;
	public String symbol;
	public String limit;
	public String interval;
	public String URL;
	
	// For HMAC SHA256
	private Long unixTime = System.currentTimeMillis();					
	private String unixTimeString = unixTime.toString();					// have top use 'Long' != primitive 'long' above in order to use toString() here
	//System.out.println(unixTimeString);
	private String query = "recvWindow=10000&timestamp=" + unixTimeString;
	//System.out.println("query: " + query);
	
	LoginSetup () {
		endpoint = "https://api.binance.com"; //api/v1/klines";
		symbol = "?symbol=BTCUSDT";
		limit = "limit=1000";
		interval = "interval=15m";
		URL = null;
	}
	
	public String getURL() {
		URL = endpoint + symbol + "&" + limit + "&" + interval;
		return URL;
	}
	
	public String generateHmac(String secret) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		Long unixTime = System.currentTimeMillis();					
		String unixTimeString = unixTime.toString();							// have top use 'Long' != primitive 'long' above in order to use toString() here
		//String unixTimeString = "1540441910401";
		String recvWindow = "10000";
		query = "recvWindow=" + recvWindow + "&timestamp=" + unixTimeString;
		
		Mac hmacSha256 = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
		hmacSha256.init(secretKey);
		String hexString = Hex.encodeHexString(hmacSha256.doFinal(query.getBytes("UTF-8")));
		return query+"&signature="+hexString;														// signature
	}
}
