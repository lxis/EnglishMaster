package com.englishmaster.tweeter.infrastructure.ui.mvvm.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

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
		Object currentItem = item;		
		Method.invoke(View, Field.get(currentItem));		
	};	
}
