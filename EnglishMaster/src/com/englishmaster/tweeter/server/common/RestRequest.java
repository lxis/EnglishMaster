package com.englishmaster.tweeter.server.common;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

public class RestRequest {
	
	public RestRequest()
	{
		GetParamDic = new ArrayList<BasicNameValuePair>();
	}
	
	public String Url;
	public ArrayList<BasicNameValuePair> GetParamDic;
}
