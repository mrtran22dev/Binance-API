package com.binance;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import com.google.gson.JsonParseException;


public class Main {

	public static void main(String[] args) { //throws IOException, InvalidKeyException, NoSuchAlgorithmException  {
		
		try {
			String secret = "ulVEFfRkliPEob5Go9mEPxDIuxWnGfa2qCSnlOYyRuSEmpwUcpQAhrvzT7UiJJkl";		// dummy secret key
			String apiKey = "6AilactyuNx2INdp7kedFQyFKcOUyCPTFw1bQxSQua6tnQ2bPN7RA00OpLM54rFJ";		// dummy api key
				
			LoginSetup login = new LoginSetup();
			Trade trade = new Trade();
			SMA sma = new SMA();
			
			// Get Kline ===========================================
			String kline = trade.getKline(login);
			System.out.println("GET KLINE: " + kline);
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = (JsonArray) parser.parse(kline);
			//System.out.println("JSONArray[0]: " + jsonArray.get(0));									// check
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String prettyJson = gson.toJson(jsonArray);
			System.out.println(prettyJson);
			
			// Get account info ==========================================
			String query = login.generateHmac(secret);
			String account = trade.getAccount(apiKey, query, login);
			System.out.println("\nACCOUNT: " + account);
			JsonObject jsonObject = (JsonObject) parser.parse(account);
			prettyJson = gson.toJson(jsonObject);
			System.out.println(prettyJson);
			
			sma.calcSMA(jsonArray, 9);																	// call 'calcSMA' method setting Simple Moving Average = 9 days
			System.out.println("\nStatus code: " + trade.getStatusCode());
			System.out.println("Host: " + trade.getHost());
			System.out.println("\nACCOUNT: " + account);
			System.out.println("Output file wrote to "
					+ "path: " + System.getProperty("user.dir"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		} catch (NoSuchAlgorithmException ae) {
			ae.printStackTrace();
		}
	}

}
