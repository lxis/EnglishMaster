package com.englishmaster.tweeter.infrastructure.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandlerGenic;

import com.google.gson.Gson;

public class BaseActivity extends Activity
{
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){	
		super.onActivityResult(requestCode, resultCode, data);		
		Bundle bundle = data.getExtras();
		String resultType = bundle.getString("ResultType");
		String result = bundle.getString("Result");
//		new Gson().fromJson(json, typeOfT) result
//		CommonSimpleHandlerGenic<Object> handler = Result.get(resultType);
//		handler.Run(item);
//		handler.Run(bundle.get("Result"));			
	}
	
	public void setResult(Object object)
	{
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("ResultType",  object.getClass().getName() );
		bundle.putString("Result",new Gson().toJson(object));
		intent.putExtras(bundle);
		setResult(1, intent);
	}
	
	public <T> T getParam(Class<T> classType )
	{
		return new Gson().fromJson(getIntent().getExtras().getString("NavigateParam"),classType);
	}

	private HashMap<String, CommonSimpleHandlerGenic<Object>> Result = new HashMap<String,CommonSimpleHandlerGenic<Object>>();		

	public void addHandler(Class classType, CommonSimpleHandlerGenic<Object> handler)
	{
		Result.put(classType.getName(), handler);
	}
	
	public void navigate(Class navigateClass,Object info)
	{		
		Intent intent = new Intent(this,navigateClass);
		Bundle b = new Bundle(); 		
		b.putString("NavigateParam", new Gson().toJson(info));
		int key = new Random().nextInt();
		b.putInt("Result", key);//避免重复
		intent.putExtras(b);
//		Result.put(key, handler);
		this.startActivityForResult(intent,key);
	}	
	
	public void navigate(Class navigateClass)
	{
		navigate(navigateClass, null);
	}
	
	
}
