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
	public NavigateHelper(Activity activity)
	{
		this.Context = activity;		
	}	
	
	Activity Context;		
	
	public void go(Class NavigateClass,Object Param,HashMap<Class, CommonSimpleHandlerGenic<Object>> Handlers)
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
