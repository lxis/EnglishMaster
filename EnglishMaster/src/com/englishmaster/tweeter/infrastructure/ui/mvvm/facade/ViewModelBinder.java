package com.englishmaster.tweeter.infrastructure.ui.mvvm.facade;

import java.lang.reflect.InvocationTargetException;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.common.GlobalUncaughtExceptionHandler;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.BindingCore;
import com.englishmaster.tweeter.presentation.ui.viewmodels.BindViewModel;
import com.englishmaster.tweeter.presentation.ui.views.BindingActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ViewModelBinder
{
	static BindingCore bind = new BindingCore();
	public static void Bind(View view,BaseViewModel viewModel) 
	{
		try
		{
			bind.bindTag(view, viewModel.getClass());
		}
		catch (NoSuchFieldException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		catch (NoSuchMethodException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		try
		{
			bind.bindData(view, viewModel);
		}
		catch (IllegalAccessException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		catch (InvocationTargetException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
	}
	public static void Bind(BindViewModel viewModel, int layoutId, Activity bindingActivity)
	{				
		View rootView = LayoutInflater.from(bindingActivity).inflate(layoutId, null);
		new ViewModelBinder().Bind(rootView , viewModel);
		bindingActivity.setContentView(rootView);				
		
	}
}