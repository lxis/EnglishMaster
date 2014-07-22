package com.englishmaster.tweeter.presentation.ui.viewmodels;

import java.lang.reflect.InvocationTargetException;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;

public class BindViewModel extends BaseViewModel
{
	public BindViewModel(String name, String content, String title)
	{
		this.Name = name;
		this.Content = content;
		this.Title = title;
	}

	public String Name = "nameaa";

	public void SetName(String name)
	{
		this.Name = name;
		NotifyPropertyChanged("Name");
	}

	public String Title;
	public String Content;

	public void SetContent(String content)
	{
		Content = content;
		NotifyPropertyChanged("Content");
	}

	public void SetTitle(String title)
	{
		Title = title;
		NotifyPropertyChanged("Title");
	}
}
