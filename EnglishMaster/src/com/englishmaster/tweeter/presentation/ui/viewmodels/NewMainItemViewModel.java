package com.englishmaster.tweeter.presentation.ui.viewmodels;

import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;
import com.englishmaster.tweeter.presentation.ui.views.MyURLSpan;

public class NewMainItemViewModel extends BaseViewModel
{
	public String TranslationText;
	public void SetTranslationText(String translationText)
	{
		this.TranslationText = translationText;
		NotifyPropertyChanged("TranslationText");
	}
	
	public int TranslationVisibility;
	public void SetTranslationVisibility(int visibility)
	{
		this.TranslationVisibility = visibility;
		NotifyPropertyChanged("TranslationVisibility");
	}
	
	public OnClickListener GoodCommand;

	public void SetGoodCommand(OnClickListener l)
	{
		this.GoodCommand = l;
		NotifyPropertyChanged("GoodCommand");
	}

	public OnClickListener BadCommand;

	public void SetBadCommand(OnClickListener l)
	{
		this.BadCommand = l;
		NotifyPropertyChanged("BadCommand");
	}
	
	public android.text.method.MovementMethod MovementMethod;
	public void SetMovementMethod(android.text.method.MovementMethod m)
	{
		this.MovementMethod = m;
		NotifyPropertyChanged("MovementMethod");
	}

	public NewMainItemViewModel()
	{
		SetIsGoodColor(UncheckedColor);
		SetIsBadColor(UncheckedColor);
		SetGoodCommand(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SetIsGood(true);
				SetIsBad(false);
			}
		});

		SetBadCommand(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SetIsBad(true);
				SetIsGood(false);
			}
		});
		SetTranslationVisibility(View.GONE);
	}	
	
	private void SetTextLink()
	{
		String[] words = Content.split(" ");
		for (int i = 0; i < words.length; i++)
			words[i] = words[i];
		String clickText = "";
		for (String word : words)
			clickText += "<a style=\"text-decoration:none\" href=\"" + word + "\">" + word + "</a> ";
		SetShowContent(Html.fromHtml("<string name=\"hello\">" + clickText + "</string>"));

		SetMovementMethod(LinkMovementMethod.getInstance());		
		if (ShowContent instanceof Spannable)
		{
			int end = ShowContent.length();
			Spannable sp = (Spannable) ShowContent;
			URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
			SpannableStringBuilder style = new SpannableStringBuilder(ShowContent);
			style.clearSpans(); // should clear old spans
			for (URLSpan url : urls)
			{
				MyURLSpan myURLSpan = new MyURLSpan(url.getURL(), context,NewMainItemViewModel.this);
				style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				style.setSpan(new ForegroundColorSpan(Color.BLACK), sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			SetShowContent(style);
		}
	}

	private int CheckedColor = Color.rgb(140, 210, 50);
	private int UncheckedColor = Color.rgb(146, 146, 146);

	public int IsGoodColor;

	public void SetIsGoodColor(int isGoodColor)
	{
		this.IsGoodColor = isGoodColor;
		NotifyPropertyChanged("IsGoodColor");
	}

	public int IsBadColor;

	public void SetIsBadColor(int isBadColor)
	{
		this.IsBadColor = isBadColor;
		NotifyPropertyChanged("IsBadColor");
	}

	public String Content;

	public void SetContent(String content)
	{
		this.Content = content;
		NotifyPropertyChanged("Content");
		SetShowContent(Content);
		SetTextLink();
	}
	
	public CharSequence ShowContent;
	public void SetShowContent(CharSequence showContent)
	{
		this.ShowContent = showContent;
		NotifyPropertyChanged("ShowContent");
	}

	public boolean IsGood = false;

	public void SetIsGood(boolean isGood)
	{
		this.IsGood = isGood;
		NotifyPropertyChanged("IsGood");
		if (isGood)
			SetIsGoodColor(CheckedColor);
		else
			SetIsGoodColor(UncheckedColor);
	}

	public boolean IsBad = false;

	public void SetIsBad(boolean isBad)
	{
		this.IsBad = isBad;
		NotifyPropertyChanged("IsBad");
		if (isBad)
			SetIsBadColor(CheckedColor);
		else
			SetIsBadColor(UncheckedColor);
	}
}
