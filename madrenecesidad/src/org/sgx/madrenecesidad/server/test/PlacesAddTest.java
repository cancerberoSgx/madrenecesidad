//package org.sgx.madrenecesidad.server.test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class PlacesAddTest {
//public static void main(String[] args) {
//	String url = "https://maps.googleapis.com/maps/api/place/delete/json"; 
//	Map<String, String> params = new HashMap<String, String>();
//	params.put("sensor", "false");
//	params.put("key", "AIzaSyAeX11OLg0zyBDlpYr2lqs4POPEjyWrooY"); 
//	test1();
//}
//
//private static void test1(String url_, Map<String, String> params) throws IOException {
//	
//    String data = "";
//    Iterator<String> it = params.keySet().iterator();
//    while (it.hasNext()) {
//		String n = (String) it.next();
//		String val = params.get(n); 
//		data+=URLEncoder.encode(n, "UTF-8")+"="+URLEncoder.encode(val, "UTF-8");
//		if(it.hasNext())
//			data+="&";
//	}
//    // Send data
//    URL url = new URL(url_);
//    URLConnection conn = url.openConnection();
//    conn.setDoOutput(true);
//    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//    wr.write(data);
//    wr.flush();
//
//    // Get the response
//    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//    String line;
//    while ((line = rd.readLine()) != null) {
//        System.out.println(line);
//    }
//    wr.close();
//    rd.close();
//}
//}
