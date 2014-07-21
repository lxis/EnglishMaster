package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandler;
import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandlerGenic;

public class PropertyChangedHandler
{	
	public Method Method;
	
	public Field Field;
	
	public android.view.View View;
	
	public String PropertyName;
	
	public void Handle(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{		
		Method.invoke(View, Field.get(item));		
	};	
}
