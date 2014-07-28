package com.englishmaster.tweeter.infrastructure.ui;


import java.util.HashMap;

import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandlerGenic;

import android.app.Activity;

public class NavigationInterface
{
	BaseActivity activity;	
	Object param;
	HashMap<Class, CommonSimpleHandlerGenic<Object>> Handlers = new HashMap<Class, CommonSimpleHandlerGenic<Object>>();
	Class classType;
	
	public NavigationInterface(BaseActivity activity,Class classType)
	{
		this.activity = activity;		
		this.classType = classType;
	}
	
	public NavigationInterface param(Object param)
	{
		this.param = param;
		return this;
	}
	
	public NavigationInterface handler(Class classType,CommonSimpleHandlerGenic<Object> handler)
	{
		Handlers.put(classType, handler);
		return this;
	}
	
	public void go()
	{
		activity.go(classType,param, Handlers);		
	}
}
