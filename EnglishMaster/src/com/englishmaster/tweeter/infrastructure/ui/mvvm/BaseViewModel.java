package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

public class BaseViewModel//ViewModel鐨勫熀纭�被锛屼箣鎵�互缁ф壙杩欎釜绫绘槸鍥犱负鍒板閮界敤context銆傚彟涓�柟闈互鍚庣殑鎵╁睍鏄渶瑕佽繖涓殑銆�
{
	public Context context;	
	
	public void setContext(Context context)
	{
		this.context = context;
	}
	
	public  HashMap<String, PropertyChangedHandler> Handlers = new HashMap<String, PropertyChangedHandler>();
	
	public void NotifyPropertyChanged(String property) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		PropertyChangedHandler handler = Handlers.get(property);
		handler.Handle(this);
	}	
}
