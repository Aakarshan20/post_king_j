package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
public class HttpConnector {
	private final String USER_AGENT = "Mozilla/5.0";
	
	//public static void main(String args[]) throws Exception{
		//HttpConnector conn = new HttpConnector();
		
		//post 例
		//System.out.println("test 2 send post request");
		//conn.sendPost();
		
		//post 例2
		//String url = "http://test.api.plus20.marq.com.tw/mpeg/fetch";
		//String[] key = {"device_id","num","coupon_id"};
		//String [] value = {"833c23fd-ab1a-43e6-a943-d62fa38e80e5","7","152"};
		//String [] result  = conn.sendPost(url, key, value);
		//for(String str : result) {
			//System.out.println(str);
		//}
	//}
	
	public void sendGet() throws Exception{
		//String url = 
	}
	
	public void sendPost() throws Exception{
		String url = "http://test.api.plus20.marq.com.tw/mpeg/fetch";
		
		URL obj =  new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		con.setRequestProperty("Accept-Language","zh-tw,en-us;q=0.7,en;q=0.3"); 
		con.setRequestProperty("Accept-Charse", "Big5,utf-8;q=0.7,*;q=0.7"); 
		con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		con.setRequestProperty("Accept-Language","zh-tw,en-us;q=0.7,en;q=0.3"); 
		con.setRequestProperty("Accept-Charse","Big5,utf-8;q=0.7,*;q=0.7");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		String urlParameters = "device_id=833c23fd-ab1a-43e6-a943-d62fa38e80e5";
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
		
  }
	public String[] sendPost(String url, String[] key, String[] value) throws Exception{//post 接收3組參數版本
		
		
		URL obj =  new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		con.setRequestProperty("Accept-Language","zh-tw,en-us;q=0.7,en;q=0.3"); 
		con.setRequestProperty("Accept-Charset", "Big5,utf-8;q=0.7,*;q=0.7"); 
		con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		con.setRequestProperty("Accept-Language","zh-tw,en-us;q=0.7,en;q=0.3"); 
		con.setRequestProperty("charset", "utf-8");//有人用大寫 有人用小寫 那我就兩個都用
		con.setRequestProperty("Charset", "UTF-8");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		String urlParameters = "";//參數字串
		
		for(int i=0; i<key.length; i++) {//循環拼字串
			urlParameters += key[i] + "=" + value[i] + "&";
		}
		

		if(urlParameters.length()>0) {//如果長度大於0 去掉末尾的&
			urlParameters = urlParameters.substring(0, urlParameters.length()-1);
		}
		
		/*2018-05-09 亂碼版本
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		*/
		//以下為亂碼解決方案1(測試中)
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr,"UTF-8"));//將輸出流編碼成UTF-8(重要)
		writer.write(urlParameters);
		writer.close();
		wr.close();
		
		int responseCode = con.getResponseCode();//取出狀態碼

		BufferedReader in = new BufferedReader(
		        				new InputStreamReader(con.getInputStream(),"UTF-8")//將輸入流編碼成UTF-8(重要)
		        			);
		String inputLine;
		StringBuffer response = new StringBuffer();//建立stringbuffer接收回傳的內容

		while ((inputLine = in.readLine()) != null) {
			inputLine = new String(inputLine.getBytes(), "utf-8");//鞭成UTF-8
			response.append(inputLine);
		}
		in.close();//關閉BufferReader
		
		String[] result = {responseCode+"", response.toString()};//將狀態與回傳值組成array 返回
		return result;
		
  } 
	
}
