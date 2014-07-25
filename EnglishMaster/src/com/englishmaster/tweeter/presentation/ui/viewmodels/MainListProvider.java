package com.englishmaster.tweeter.presentation.ui.viewmodels;

import java.util.ArrayList;

import android.os.AsyncTask;

import com.englishmaster.tweeter.data.server.services.TweeterService;
import com.englishmaster.tweeter.domain.model.Article;
import com.englishmaster.tweeter.presentation.ui.adapters.MainListAdapter;
import com.englishmaster.tweeter.presentation.ui.views.MainActivity;

public class MainListProvider
{
	ArrayList<Article> data;

	public MainListProvider()
	{
		data = new ArrayList<Article>();		
	}

	private int preloadCount = 1;
	private int singleCount = 2;

	private int lastIndex = 1;

	public boolean IsNeedLoadData(int totalCount, int lastPosition)
	{
		return totalCount <= lastPosition + 1 + preloadCount;
	}

	public ArrayList<Article> LoadData()
	{
		ArrayList<Article> articles = new TweeterService().GetTweeters(lastIndex, singleCount);
		data = articles;
		lastIndex = lastIndex + singleCount;
		return data;
	}

}
