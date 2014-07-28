package com.englishmaster.tweeter.infrastructure.ui;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandlerGenic;
import com.google.gson.Gson;

public class NavigateHelper
{
	public static NavigateHelper from(Activity activity)
	{
		NavigateHelper helper = new NavigateHelper();
		helper.Context = activity;
		return helper;
	}
	
	public NavigateHelper to(Class classType)
	{
		this.NavigateClass = classType;
		return this;
	}
	
	public NavigateHelper param(Object param)
	{
		this.Param = param;
		return this;
	}
	
	public NavigateHelper handler(Class classType,CommonSimpleHandlerGenic<Object> handler)
	{
		Handlers.put(classType, handler);
		return this;
	}
	
	Class NavigateClass;
	
	Object Param;
	
	Activity Context;
	
	HashMap<Class, CommonSimpleHandlerGenic<Object>> Handlers = new HashMap<Class, CommonSimpleHandlerGenic<Object>>();
	
	void addHandler(Class classType,CommonSimpleHandlerGenic<Object> handler)	
	{
		Handlers.put(classType, handler);
	}
	
	public void go()
	{		
		Intent intent = new Intent(Context,NavigateClass);
		Bundle b = new Bundle(); 		
		b.putString("NavigateParam", new Gson().toJson(Param));
		int key = new Random().nextInt();
		b.putInt("Result", key);//避免重复
		intent.putExtras(b);
//		Result.put(key, handler);
		Context.startActivityForResult(intent,key);
	}	
}
