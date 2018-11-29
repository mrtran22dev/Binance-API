package com.binance;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) throws IOException, ParseException, InvalidKeyException, NoSuchAlgorithmException {
		
		String secret = "Enter your secret key";
		String apiKey = "Enter your api key";
		
		LoginSetup login = new LoginSetup();
		Trade trade = new Trade();
		FileRW file = new FileRW();
		SMA sma = new SMA();
		
		
		// Get Kline ===========================================
		String kline = trade.getKline(login);
		System.out.println("GET KLINE: " + kline);
		System.out.println("status code: " + trade.getStatusCode());
		System.out.println("host: " + trade.getHost());
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(kline);
		System.out.println("JSONArray[0]: " + jsonArray.get(0));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(jsonArray);
		System.out.println(prettyJson);
		//file.writeFile(prettyJson.toString());
		
		// Get account info ==========================================
		String query = login.generateHmac(secret);
		String account = trade.getAccount(apiKey, query, login);
		System.out.println("ACCOUNT: " + account);
		JSONObject jsonObject = (JSONObject) parser.parse(account);
		prettyJson = gson.toJson(jsonObject);
		System.out.println(prettyJson);
		//trade.getAccount(apiKey, signature, login);
		
		sma.SMA(jsonArray, 9);								// call 'SMA' method setting Simple Moving Average = 9 days
	}

}
