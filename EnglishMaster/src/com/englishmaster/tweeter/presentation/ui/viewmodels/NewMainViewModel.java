package com.englishmaster.tweeter.presentation.ui.viewmodels;

import android.graphics.Color;
import android.opengl.Visibility;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;

public class NewMainViewModel extends BaseViewModel
{

	public String Title;
	
	public String BottomButtonOneText;
	
	public String BottomButtonTwoText;
	
	public int BottomButtonOneTextColor;
	
	public int BottomButtonTwoTextColor;
	
	public NewMainViewModel()
	{
		SetTitle("英语大师");
		SetBottomButtonOneText("学习");			
		SetBottomButtonTwoText("成绩");
		SetBottomButtonOneTextColor(Color.rgb(140,210,50));
		SetBottomButtonTwoTextColor(Color.rgb(255,255,255));
	}
	
	public void SetTitle(String title)
	{
		this.Title = title;
		NotifyPropertyChanged("Title");
	}
	
	public void SetBottomButtonOneText(String text)
	{
		this.BottomButtonOneText = text;
		NotifyPropertyChanged("BottomButtonOneText");
	}
	
	public void SetBottomButtonTwoText(String text)
	{
		this.BottomButtonTwoText = text;
		NotifyPropertyChanged("BottomButtonTwoText");
	}
	
	public void SetBottomButtonOneTextColor(int color)
	{
		this.BottomButtonOneTextColor = color;
		NotifyPropertyChanged("BottomButtonOneTextColor");
	}
	
	public void SetBottomButtonTwoTextColor(int color)
	{
		this.BottomButtonTwoTextColor = color;
		NotifyPropertyChanged("BottomButtonTwoTextColor");
	}
	
	
}
