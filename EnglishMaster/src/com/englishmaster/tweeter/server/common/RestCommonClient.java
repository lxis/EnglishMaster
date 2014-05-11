package com.englishmaster.tweeter.server.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RestCommonClient {

	public String GetStringFromNetwork(String url) {
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		try {
			HttpResponse hr = hc.execute(hg);
			HttpEntity he = hr.getEntity();
			InputStream is = he.getContent();
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
            StringBuffer buf = new StringBuffer();  
            String line;  
            while (null != (line = br.readLine())) {  
                buf.append(line).append("\n");  
            }  
            String responseString = buf.toString();  
            return  responseString;			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream GetStreamFromNetwork(String url)
	{
		URL myFileUrl = null; 
		InputStream is = null; 
		try 
		{ 
			myFileUrl = new URL(url); 
		} 
		catch (MalformedURLException e){e.printStackTrace();} 
		try 
		{ 
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection(); 
			conn.setDoInput(true); 
			conn.connect(); 
			is = conn.getInputStream(); 
		}
		catch (IOException e){e.printStackTrace();} 
		return is; 
	}
}
