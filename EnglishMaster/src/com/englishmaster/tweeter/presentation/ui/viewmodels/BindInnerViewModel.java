package com.englishmaster.tweeter.presentation.ui.viewmodels;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;

public class BindInnerViewModel extends BaseViewModel
{
	public String Bind = "Inner";
	

	public void SetBind(String bind)
	{
		Bind = bind;
		NotifyPropertyChanged("Bind");
	}
}
