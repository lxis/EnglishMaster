package com.englishmaster.tweeter.server.service;

import com.englishmaster.tweeter.server.common.RestClient;
import com.englishmaster.tweeter.server.common.RestRequest;
import com.englishmaster.tweeter.server.dto.TweetsResponseDTO;
import com.englishmaster.tweeter.server.dto.WordMeanDTO;

public class YoudaoService {
	public String GetWord(String word)
	{
		String url = "http://fanyi.youdao.com/openapi.do?keyfrom=EnglishMaster&key=257445074&type=data&doctype=json&version=1.1&q="+word.toLowerCase();
		RestRequest request = new RestRequest();
		request.Url = url;			
		WordMeanDTO jsonObject = new RestClient().GetDtoFromServer(request, WordMeanDTO.class);		
		return jsonObject.translation[0];			
	}
}
