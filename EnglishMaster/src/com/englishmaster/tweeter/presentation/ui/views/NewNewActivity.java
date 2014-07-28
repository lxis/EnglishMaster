package com.englishmaster.tweeter.presentation.ui.views;


import android.content.Intent;
import android.os.Bundle;

import com.englishmaster.tweeter.infrastructure.ui.BaseActivity;
import com.englishmaster.tweeter.presentation.ui.views.NewMainActivity.MainPageParam;

public class NewNewActivity extends BaseActivity
{
	public static class NewNewActivityParam
	{
		public String Title;
		public String Message;			
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{	
		super.onCreate(savedInstanceState);

		newNavigate();	
	}

	private void newNavigate()
	{
		NewNewActivityParam param1 = getParam(NewNewActivityParam.class);				
		MainPageParam param = new MainPageParam();
		param.Title = "aaa";		
		setResult(param);
	}
	
	private void oldNavigate()
	{
		Intent intent = getIntent();
		String title =  intent.getStringExtra("title");
		String message = intent.getStringExtra("message");
		
		Intent newIntent = new Intent();
		newIntent.putExtra("title", title);
		
		setResult(NewMainActivity.MainPageParamRequestCode, newIntent);
	}
}
