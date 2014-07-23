package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandler;
import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandlerGenic;

public class PropertyChangedHandler
{	
	public Method Method;
	
	public ArrayList<Field> Fields = new ArrayList<Field>();
	
	public android.view.View View;
	
	public String PropertyName;
	
	public Field getLastField()
	{
		return Fields.get(Fields.size()-1);
	}
	
	public void Handle(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{		
		Object currentItem = item;
		for(Field field:Fields)		
			currentItem = field.get(currentItem);				
		Method.invoke(View, currentItem);		
	};	
}
