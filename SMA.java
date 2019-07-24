package com.binance;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;
import com.google.gson.JsonArray;

public class SMA {
	
	SMA() {												// Constructor
		// nothing
		System.out.println("Constructor created");
	}
	
	class Candle {
		private Object time, open, high, low, close, sma;
	}
	Candle cTemp = new Candle();
	
	private ArrayList<Candle> klineArray = new ArrayList<Candle>(); 
	private JsonArray arr = new JsonArray();
	FileRW writer = new FileRW();
	
	public void calcSMA(JsonArray jsonArray, int days) throws IOException {
		
		//System.out.println(jsonSMA25.size());

		Candle klineTemp = new Candle();
		double[] closeArray = new double[days];
		double sum = 0;
		//LocalDateTime local;
		String klineString = "";
		for (int j=0;j<jsonArray.size();j++) {

			Candle kline = new Candle();									// ***** had to create Candle object here (inside loop) to keep creating a new instance??
			arr = (JsonArray) jsonArray.get(j);	
			//kline.time = arr.get(0);										// returns Long type
			//kline.open = arr.get(1);										// returns String type
			//kline.high = arr.get(2);										// returns String type
			//kline.low = arr.get(3);										// returns String type
			//kline.close = arr.get(4);										// returns String type
			
			// Casting objects above to type 'double'
			kline.time = LocalDateTime.ofInstant(Instant.ofEpochMilli(arr.get(0).getAsLong()), TimeZone.getDefault().toZoneId());
			kline.open = (arr.get(1).getAsDouble());						//arr.get(1) returns String type
			kline.high = (arr.get(2).getAsDouble());						//arr.get(2) returns String type
			kline.low = (arr.get(3).getAsDouble());							//arr.get(3) returns String type
			kline.close = (arr.get(4).getAsDouble());						//arr.get(4) returns String type
			kline.sma = "n/a";
			
			if (j<days-1) {
				closeArray[j] = (Double) kline.close;
				klineArray.add(kline);
			} else if (j==days-1) {
				closeArray[j] = (Double) kline.close;
				for (double value : closeArray) {
					sum = sum + value;
					//System.out.println(value);
				}
				kline.sma = sum/days;
				klineArray.add(kline);
			} else {														// j>24
				sum = 0;
				for(int n=0; n<closeArray.length-1; n++) {					// shift existing array to left
					closeArray[n] = closeArray[n+1];
				}
				closeArray[days-1] = (Double) kline.close;
				for (double value : closeArray) {
					sum = sum + value;
				}
				kline.sma = sum/days;
				klineArray.add(kline);
			}

		}
		
		for (int m=0; m<klineArray.size(); m++) {
			klineTemp = klineArray.get(m);
			klineString = klineString + (klineTemp.time + ", " + klineTemp.open + ", " + klineTemp.high + ", " + klineTemp.low + ", " + klineTemp.close + ", " + klineTemp.sma + "\r\n");
			
		}
		
		//System.out.println(klineString);									// check
		writer.writeFile(klineString);	
	}	

}
