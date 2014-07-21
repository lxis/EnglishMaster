package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.lang.reflect.InvocationTargetException;

import android.view.View;

public class ViewModelBinder
{
	BindingCore bind = new BindingCore();
	public void Bind(View view,BaseViewModel viewModel) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		bind.bindTagSingleControl(view, viewModel.getClass());
		bind.bindDataSingleControl(view, viewModel);
	}
}
