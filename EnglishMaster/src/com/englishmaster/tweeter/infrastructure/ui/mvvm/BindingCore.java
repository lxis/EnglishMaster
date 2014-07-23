package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.model.PropertyChangedHandler;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.model.TagBinding;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.model.TagBindingAnalysisResult;

import android.view.View;

public class BindingCore
{
	public void bindTag(View control,Class modelClass) throws NoSuchFieldException, NoSuchMethodException
	{
		ArrayList<View> controls = VisualHelper.getAllChildrenControls(control);
		for (View controlItem : controls)
		{
			Object tag = controlItem.getTag();
			if (!checkOriginalTag(tag)) continue;
			bindTagSingleControl(controlItem, tag.toString(),modelClass);
		}
	}
	
	private boolean checkOriginalTag(Object tag)//鏄惁鏈夋晥鐨勬湭瑙ｆ瀽杩囩殑Tag
	{
		return tag != null && !(tag instanceof TagBinding);
	}
	
	public void bindTagSingleControl(View controlItem,Class modelClass) throws NoSuchFieldException, NoSuchMethodException
	{
		bindTagSingleControl(controlItem, controlItem.getTag().toString(),modelClass);
	}

	private void bindTagSingleControl(View controlItem, String tag,Class modelClass) throws NoSuchFieldException, NoSuchMethodException
	{
		TagBinding tagBinding = new TagBinding();
		for (TagBindingAnalysisResult tagInfo : new BindingAnalyst().loadBindingParamText(tag))
			tagBinding.Operations.put(tagInfo.FieldName,loadBindingHandler(controlItem, tagInfo,modelClass));
		controlItem.setTag(tagBinding);		
	}

	private PropertyChangedHandler loadBindingHandler(View controlItem, TagBindingAnalysisResult tagInfo,Class modelClass) throws NoSuchFieldException, NoSuchMethodException
	{		
		PropertyChangedHandler handler = new PropertyChangedHandler();
		handler.PropertyName =  tagInfo.FieldName;
		
		Class currentClass = modelClass;
		for(String fieldNameString:tagInfo.FieldNameStrings)
		{
			Field field = currentClass.getField(tagInfo.FieldNameStrings.get(0));
			handler.Fields.add(field);
			currentClass = field.getType();
		}
		//for//循环取Field
		
		handler.Method = ReflectHelper.getMethodByClassAndType(controlItem.getClass(), handler.getLastField().getType(), tagInfo.MethodNameString);
		handler.View = controlItem;		
		return handler;
	}
	
	////////////////
	
	public void bindData(View convertView, BaseViewModel item) throws IllegalAccessException, InvocationTargetException
	{
		ArrayList<View> controls = VisualHelper.getAllChildrenControls(convertView);
		for (View controlItem : controls)
		{
			Object tag = controlItem.getTag();
			if (!checkBindedTag(tag)) continue;
			bindDataSingleControl(controlItem, item);
		}
	}
	
	public void bindDataSingleControl(View convertView, BaseViewModel item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		HashMap<String, PropertyChangedHandler> operations = ((TagBinding)convertView.getTag()).Operations;
		Iterator<Entry<String, PropertyChangedHandler>> entryKeyIterator = operations.entrySet().iterator();
		       while (entryKeyIterator.hasNext()) {
		            Entry<String, PropertyChangedHandler> e = entryKeyIterator.next();			            
		            e.getValue().Handle(item);
		 }
		mergeHandler(item, operations);
	}

	private void mergeHandler(BaseViewModel item, HashMap<String, PropertyChangedHandler> operations)
	{
		Iterator<Entry<String, PropertyChangedHandler>> entryKeyIterator = operations.entrySet().iterator();
		       while (entryKeyIterator.hasNext()) {
		            Entry<String, PropertyChangedHandler> e = entryKeyIterator.next();
		            String key = e.getKey();
		            if(item.Handlers.containsKey(key))
		            {
		        	    item.Handlers.get(key).add(e.getValue());
		            }
		            else
		            {
		        	    ArrayList<PropertyChangedHandler> handlers = new ArrayList<PropertyChangedHandler>();
		        	    handlers.add(e.getValue());
		        	    item.Handlers.put(key, handlers);		        	    
		            }
		 }
	}
	

	private boolean checkBindedTag(Object tag)
	{
		return tag != null && tag instanceof TagBinding;
	}

}
