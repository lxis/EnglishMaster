package com.englishmaster.tweeter.data.server.common;

import java.io.InputStream;

import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class RestClient {
	
	public static <T> T GetDtoFromServer(RestRequest request ,Class<T> classType)
	{
		RestCommonClient restCommonClient = new RestCommonClient();
		String url = GenerateUrlByRequest(request);				
			String result = restCommonClient.GetStringFromNetwork(url);
			if(classType == String.class)
				return (T)result;
			T jsonObject = new Gson().fromJson(result, classType);
			return jsonObject;				
	}

	private static String GenerateUrlByRequest(RestRequest request) {
		if(request.GetParamDic.size()==0)
			return request.Url;
		request.Url+= "?";
		for(BasicNameValuePair getParam:request.GetParamDic)		
			request.Url+=getParam.getName()+"="+getParam.getValue()+"&";		
		return request.Url;
	}
	
	public static InputStream GetStreamFromServer(RestRequest request)
	{
		RestCommonClient restCommonClient = new RestCommonClient();
		String url = GenerateUrlByRequest(request);
		InputStream result = restCommonClient.GetStreamFromNetwork(url);		
		return result;
	}
}
