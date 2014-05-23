package com.englishmaster.tweeter.server.service;

import java.util.ArrayList;

import com.englishmaster.tweeter.domain.Article;
import com.englishmaster.tweeter.server.common.RestClient;
import com.englishmaster.tweeter.server.common.RestRequest;
import com.englishmaster.tweeter.server.dto.TweeterDTO;
import com.englishmaster.tweeter.server.dto.TweetersDataDTO;
import com.englishmaster.tweeter.server.dto.TweetsResponseDTO;

public class TweeterService {
	public ArrayList<Article> GetTweeters(int lastIndex, int singleCount)
	{
		RestRequest request = GenerateRequest(lastIndex,singleCount);
		TweetsResponseDTO jsonObject = new RestClient().GetDtoFromServer(request, TweetsResponseDTO.class);
		return ConvertDtoToDomain(jsonObject);		
	}

	private ArrayList<Article> ConvertDtoToDomain(TweetsResponseDTO jsonObject) {
		ArrayList<Article> data = new ArrayList<Article>();
		for(TweeterDTO tweeter: jsonObject.Data.Articles)
		{
			Article article = new Article();
			article.Content = tweeter.Content;
			data.add(article);
		}
		return data;
	}

	private RestRequest GenerateRequest(int lastIndex, int singleCount) {
		RestRequest request = new RestRequest();
		request.Url = "http://182.92.4.28/articlehandler.ashx?in="+(lastIndex+1)+"&rn="+singleCount;		
		return request;
	}
}
