package com.englishmaster.tweeter.infrastructure.ui.mvvm.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import com.englishmaster.tweeter.infrastructure.common.GlobalUncaughtExceptionHandler;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.PropertyChangedHandler;

import android.content.Context;

public class BaseViewModel
{
	public Context context;

	public void setContext(Context context)
	{
		this.context = context;
	}

	public HashMap<String, ArrayList<PropertyChangedHandler>> Handlers = new HashMap<String, ArrayList<PropertyChangedHandler>>();

	public void NotifyPropertyChanged(String property)
	{
		ArrayList<PropertyChangedHandler> handlers = Handlers.get(property);
		if(handlers == null)
			return;
		for (PropertyChangedHandler handler : handlers)
			try
			{
				handler.Handle(this);
			}
			catch (IllegalAccessException e)
			{
				GlobalUncaughtExceptionHandler.AddUnhandledException(e);
			}
			catch (IllegalArgumentException e)
			{
				GlobalUncaughtExceptionHandler.AddUnhandledException(e);
			}
			catch (InvocationTargetException e)
			{
				GlobalUncaughtExceptionHandler.AddUnhandledException(e);
			}

	}
}
