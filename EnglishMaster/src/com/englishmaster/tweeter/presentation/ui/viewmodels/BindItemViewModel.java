package com.englishmaster.tweeter.presentation.ui.viewmodels;

import java.lang.reflect.InvocationTargetException;

import android.view.View;
import android.view.View.OnClickListener;

import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandler;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.BaseViewModel;

public class BindItemViewModel extends BaseViewModel
{
	public OnClickListener ShowChange = new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0)
		{
				Handler.Run();
		}
	}; 
	
	public CommonSimpleHandler Handler = null;
	
	public BindItemViewModel(String title,String text)
	{
		this.Title = title;
		this.Content = text;
	}
	
	public String Title;
	public String Content;
	
	public void SetContent(String content) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Content = content;
		NotifyPropertyChanged("Content");
	}
}
