package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.Card;
import entity.Payment;

/**
 * Class gui nhan du lieu voi server qua API
 */
public class API {
	
	/**
	 * Thuoc tinh giup format ngay thang theo dinh dang
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Thuoc tinh giup log thong tin ra console
	 */
	private static Logger LOGGER = Util.getLogger(Util.class.getName());
	
	/**
	 * Phuong thuc giup goi cac api dang GET
	 * @param url: duong dan toi server can request
	 * @param token: doan ma can cung cap de xac thuc nguoi dung
	 * @return response: phan hoi tu server (string)
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		
		// phan 1: setup
		LOGGER.info("Request URL: " + url + "\n");
		HttpURLConnection conn = setUpConnection(token, url);
		
		// phan 2: doc du lieu tra ve tu server
		String response = readRespone(conn);
		return response;
	}

	int var;
	
	/**
	 * Phuong thuc giup goi cac api dang POST
	 * @param url: duong dan toi server can request
	 * @param data: du lieu dua len server de xu ly (JSON)
	 * @return response: phan hoi tu server (string)
	 * @throws Exception
	 */
	public static String post(String url, String data
//			, String token
	) throws Exception {
		allowMethods("PATCH");
		
		// phan 1: Setup
		String payload = data;
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
		HttpURLConnection conn = setUpConnection(null, url);
		
		// phan 2: gui du lieu
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		
		// phan 3: doc du lieu gui ve tu server
		String response = readRespone(conn);
		return response;
	}
	
	/**
	 * Phuong thuc cho phep goi cac loai giao thuc API khac nhau nhu PATCH, PUT, ... (chi hoat dong voi Java 11)
	 * @deprecated chi hoat dong voi Java <= 11
	 * @param methods: giao thuc can cho phep (PATCH, PUT, ...)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Thiet lap ket noi toi server
	 * @param token doan ma de xac thuc nguoi dung
	 * @param url duong dan toi server can request 
	 * @return connection
	 * @throws Exception
	 */
	private static HttpURLConnection setUpConnection(String token, String url) throws Exception{
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("PATCH");
		conn.setRequestProperty("Content-Type", "application/json");
		if(token != null) {
			conn.setRequestProperty("Authorization", "Bearer " + token);
		}
		
		return conn;
	}
	
	/**
	 * Doc du lieu tra ve tu server
	 * @param conn: ket noi toi server
	 * @return response: phan hoi tra ve tu server
	 * @throws IOException
	 */
	private static String readRespone (HttpURLConnection conn) throws IOException {
		// get data sent back from server
		BufferedReader in; 
		String inputLine; 
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			} 
		else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder(); 
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine); 
		response.append(inputLine + "\n"); 
		in.close();
		LOGGER.info("Response Info: " + response.substring(0, response. length() - 1).toString()); 
		return response.substring(0, response. length() - 1).toString();
	}

}
