package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.util.ArrayList;
import java.util.HashMap;

//杩欎釜鏄粦瀹氬湪Tag涓婄殑涓滆タ
public class TagBinding
{
	public Object OriginalTag;
	public HashMap<String, PropertyChangedHandler> Operations = new HashMap<String, PropertyChangedHandler>();
}
