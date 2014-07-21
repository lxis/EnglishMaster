package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import android.content.Context;

public class BaseViewModel//ViewModel鐨勫熀纭�被锛屼箣鎵�互缁ф壙杩欎釜绫绘槸鍥犱负鍒板閮界敤context銆傚彟涓�柟闈互鍚庣殑鎵╁睍鏄渶瑕佽繖涓殑銆�
{
	public Context context;
	public void setContext(Context context)
	{
		this.context = context;
	}
}
