package com.smtp.mail.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class GETRequest {
	static String subString = null;

	public static String gspPostRequest() throws IOException {
		URL urlForGetRequest = new URL("http://13.126.145.36/gsp/authenticate?grant_type=token");
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("POST");
		conection.setRequestProperty("gspappid", "9B355F5EE9914F2398614251F86792B2");
		conection.setRequestProperty("gspappsecret", "50E3EABAGCDD1G42A7G95EBG5571AE439D18");

		int responseCode = conection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			StringBuilder response = new StringBuilder();
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			
			in.close();
			
			String accesstoken = response.toString();
			System.out.println("JSON String Result " + accesstoken);
			String[] str = accesstoken.split(":");
			String token = str[1];
			String[] tkn = token.split(",");
			String tkn1 = tkn[0];
			System.out.println("POST Token: " + tkn1);
			subString = tkn1.replaceAll("\"", "");
			System.out.println("POST Token1: " + subString);
		} else {
			System.out.println("POST NOT WORKED");
		}
		return readLine;

	}

	public static void gspGetRequest() throws IOException {

		URL obj = new URL("http://13.126.145.36/test/gstn/commonapi/search?action=TP&gstin=33GSPTN9771G3ZP");
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("GET");
		postConnection.setRequestProperty("Authorization", "Bearer " + subString);

		int responseCode = postConnection.getResponseCode();
		System.out.println("GET Response Code :  " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
		String output;

		StringBuilder response = new StringBuilder();
		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		
		in.close();
		
		System.out.println("GET Response:-" + response.toString());
		String acctoken = response.toString();
		String[] str = acctoken.split(":");
		String token = str[1];
		String[] tkn = token.split(",");
		String tkn1 = tkn[0];
		System.out.println("GET Token: " + tkn1);
		subString = tkn1.replaceAll("\"", "");
		System.out.println("GET Token1: " + subString);
		String eStr = subString;
		Base64.Decoder decoder = Base64.getDecoder();
		String dStr = new String(decoder.decode(eStr));
		System.out.println("GET Decoded Message: " + dStr);
	}

	public static void main(String[] args) throws IOException {
		GETRequest.gspPostRequest();
		GETRequest.gspGetRequest();

	}
}
