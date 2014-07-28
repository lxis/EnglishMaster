package com.englishmaster.tweeter.data.server.common;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

public class RestRequest {		
	public String Url;
	public ArrayList<BasicNameValuePair> GetParamDic= new ArrayList<BasicNameValuePair>();
	public ArrayList<BasicNameValuePair> PostParamDic= new ArrayList<BasicNameValuePair>();
}
