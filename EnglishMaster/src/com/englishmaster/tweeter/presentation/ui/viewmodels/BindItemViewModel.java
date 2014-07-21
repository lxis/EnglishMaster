package com.englishmaster.tweeter.presentation.ui.viewmodels;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.BaseViewModel;

public class BindItemViewModel extends BaseViewModel
{
	public BindItemViewModel(String title)
	{
		this.Title = title;
	}
	
	public String Title;
}
