package com.englishmaster.tweeter.presentation.ui.viewmodels;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;

public class NewMainItemViewModel extends BaseViewModel
{		
	public NewMainItemViewModel()
	{
		
	}
	
	public String Content;
	public void SetContent(String content)
	{
		this.Content =content;
		NotifyPropertyChanged("Content");
	}
	
	public boolean IsGood = false;
	public void SetIsGood(boolean isGood)
	{
		this.IsGood = isGood;
		NotifyPropertyChanged("IsGood");
	}
	
	public boolean IsBad = false;
	public void SetIsBad(boolean isBad)
	{
		this.IsBad = isBad;
		NotifyPropertyChanged("IsBad");
	}
}
