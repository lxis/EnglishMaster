package com.englishmaster.tweeter.presentation.ui.viewmodels;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.BaseViewModel;

public class BindItemViewModel extends BaseViewModel
{
	public BindItemViewModel(String title,String text)
	{
		this.Title = title;
		this.Content = text;
	}
	
	public String Title;
	public String Content;
}
