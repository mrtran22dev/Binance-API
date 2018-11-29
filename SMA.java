package com.binance;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

import org.json.simple.JSONArray;

public class SMA {
	
	class Candle {
		private Object time, open, high, low, close, sma;
	}
	//Candle c = new Candle();
	Candle cTemp = new Candle();
	
	private ArrayList<Candle> klineArray = new ArrayList<Candle>(); 
	private JSONArray arr = new JSONArray(); 
	FileRW writer = new FileRW();
	
	public void SMA (JSONArray jsonArray, int days) throws IOException {
		
		//System.out.println(jsonSMA25.size());

		Candle klineTemp = new Candle();
		double[] closeArray = new double[days];
		double sum = 0;
		//LocalDateTime local;
		String klineString = "";
		for (int j=0;j<jsonArray.size();j++) {

			Candle kline = new Candle();				// ***** had to create Candle object here (inside loop) to keep creating a new instance??
			arr = (JSONArray) jsonArray.get(j);	
			//kline.time = arr.get(0);					// Long type
			//kline.open = arr.get(1);					// String type
			//kline.high = arr.get(2);					// String type
			//kline.low = arr.get(3);					// String type
			//kline.close = arr.get(4);					// String type
			
			// Casting objects above to type 'double'
			kline.time = LocalDateTime.ofInstant(Instant.ofEpochMilli((long) arr.get(0)), TimeZone.getDefault().toZoneId());
			kline.open = Double.parseDouble((String) arr.get(1));	//arr.get(1);					// String type
			kline.high = Double.parseDouble((String) arr.get(2));	//arr.get(2);					// String type
			kline.low = Double.parseDouble((String) arr.get(3));	//arr.get(3);						// String type
			kline.close = Double.parseDouble((String) arr.get(4));	//arr.get(4);					// String type
			kline.sma = "n/a";
			
			//if (j<24) {
			if (j<days-1) {
				closeArray[j] = (double) kline.close;
				klineArray.add(kline);
			//} else if (j==24) {
			} else if (j==days-1) {
				closeArray[j] = (double) kline.close;
				for (double value : closeArray) {
					sum = sum + value;
					//System.out.println(value);
				}
				kline.sma = sum/days;
				klineArray.add(kline);
			} else {	// j>24
				sum = 0;
				for(int n=0; n<closeArray.length-1; n++) {					// shift existing array to left
					closeArray[n] = closeArray[n+1];
				}
				closeArray[days-1] = (double) kline.close;
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
		
		//System.out.println(klineString);
		writer.writeFile(klineString);	
	}
}
