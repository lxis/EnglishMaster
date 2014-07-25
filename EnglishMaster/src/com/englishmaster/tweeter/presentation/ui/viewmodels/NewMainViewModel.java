package com.englishmaster.tweeter.presentation.ui.viewmodels;

import java.util.ArrayList;

import android.graphics.Color;
import android.opengl.Visibility;
import android.view.View;

import android.view.View.OnClickListener;

import com.englishmaster.tweeter.domain.model.Article;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;

public class NewMainViewModel extends BaseViewModel
{
	private MainListProvider listProvider = new MainListProvider();

	public OnClickListener LearnCommand;

	public OnClickListener GradeCommand;

	public String Title;

	public String BottomButtonOneText;

	public String BottomButtonTwoText;

	public int BottomButtonOneTextColor;

	public int BottomButtonTwoTextColor;

	public int LearnViewVisibility;

	public int GradeViewVisibility;

	public int CurrentButtonColor = Color.rgb(140, 210, 50);

	public int ReleasedButtonColor = Color.rgb(255, 255, 255);

	public NewMainViewModel()
	{
		SetTitle("英语大师");
		SetBottomButtonOneText("学习");
		SetBottomButtonTwoText("成绩");
		SetBottomButtonOneTextColor(CurrentButtonColor);
		SetBottomButtonTwoTextColor(ReleasedButtonColor);
		SetLearnViewVisibility(View.VISIBLE);
		SetGradeViewVisibility(View.GONE);
		SetLearnCommand(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SetLearnViewVisibility(View.VISIBLE);
				SetGradeViewVisibility(View.GONE);
				SetBottomButtonOneTextColor(CurrentButtonColor);
				SetBottomButtonTwoTextColor(ReleasedButtonColor);
			}
		});
		SetGradeCommand(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SetLearnViewVisibility(View.GONE);
				SetGradeViewVisibility(View.VISIBLE);
				SetBottomButtonOneTextColor(ReleasedButtonColor);
				SetBottomButtonTwoTextColor(CurrentButtonColor);
			}
		});
	}

	public ArrayList<NewMainItemViewModel> LoadData()
	{
		ArrayList<NewMainItemViewModel> viewModels = new ArrayList<NewMainItemViewModel>();
		for(Article article : listProvider.LoadData())
		{
			NewMainItemViewModel viewModel = new NewMainItemViewModel();			
			viewModel.SetIsBad(article.IsBad);
			viewModel.SetIsGood(article.IsGood);
			viewModel.SetContent(article.Content);
			viewModels.add(viewModel);
		}		
		return  viewModels;
	}

	public boolean IsNeedLoadData(int totalCount, int lastPosition)
	{
		return listProvider.IsNeedLoadData(totalCount, lastPosition);
	}

	public void SetLearnCommand(OnClickListener l)
	{
		this.LearnCommand = l;
		NotifyPropertyChanged("LearnCommand");
	}	

	public void SetGradeCommand(OnClickListener l)
	{
		this.GradeCommand = l;
		NotifyPropertyChanged("GradeCommand");
	}

	public void SetTitle(String title)
	{
		this.Title = title;
		NotifyPropertyChanged("Title");
	}

	public void SetLearnViewVisibility(int visibility)
	{
		this.LearnViewVisibility = visibility;
		NotifyPropertyChanged("LearnViewVisibility");
	}

	public void SetGradeViewVisibility(int visibility)
	{
		this.GradeViewVisibility = visibility;
		NotifyPropertyChanged("GradeViewVisibility");
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
