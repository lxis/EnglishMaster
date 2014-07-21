package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;




import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.common.GlobalUncaughtExceptionHandler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//鏉庝韩鍐欑殑锛屾瘮杈冪硻锛屽埆鐢�
public class BindingAdapter<T extends BaseViewModel> extends BaseAdapter// T鏄垪琛ㄦ暟鎹簮鐨勭被鍨嬶紝Adapter缁ф壙鑷父鐢ㄧ殑BaseAdapter锛岃瘯鍥炬敼杩涗娇鐢ㄧ殑鏁堢巼鍜岃川閲�
{
	protected ArrayList<T> data;// 鍒楄〃闇�亶鍘嗙殑鏁版嵁婧�
	protected Context context;// Adapter杩涜涓�簺鐣岄潰鎿嶄綔鎵�緷璧栫殑鐣岄潰涓婁笅鏂�
	protected int layoutId;// 褰撳墠鍒楄〃椤圭殑瀵瑰簲Layout鐨処D銆�
	protected Class modelClass;

	public BindingAdapter(ArrayList<T> data, Context context, int layoutId, Class modelClass)
	{
		this.data = data;
		this.context = context;
		this.layoutId = layoutId;
		this.modelClass = modelClass;
		for (T item : data)
			item.setContext(context);
	}

	@Override
	public int getCount()// 閫氳繃榛樿瀹炵幇绠�寲浜嗚皟鐢�
	{
		return data.size();
	}

	@Override
	public Object getItem(int position)// 閫氳繃榛樿瀹炵幇绠�寲浜嗚皟鐢�
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position)// 閫氳繃榛樿瀹炵幇绠�寲浜嗚皟鐢�
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)// GetView鏄暣涓猘dapter澶勭悊鐨勬牳蹇�
	{
		try
		{
			return getViewInternal(position, convertView);
		}
		catch (IllegalArgumentException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		catch (IllegalAccessException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		catch (InvocationTargetException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		catch (NoSuchFieldException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		catch (NoSuchMethodException e)
		{
			GlobalUncaughtExceptionHandler.AddUnhandledException(e);
		}
		return null;
	}

	private View getViewInternal(int position, View convertView) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException
	{
		if (data.get(position) == null) return null;// 鍏堟鏌ユ暟鎹槸鍚﹀瓨鍦�
		if (convertView == null)// 濡傛灉褰撳墠view鏄痭ull锛屽垯寮�鍒涘缓view
		{
			convertView = createView(layoutId);// 鍒涘缓view
			setDataInternal(position, convertView, layoutId);// 杩欓噷鎵ц瀹㈡埛閫昏緫锛屾牴鎹暟鎹view杩涜鎿嶄綔銆傚湪鍒涘缓瀹屾垚涔嬪悗锛屾妸璇iew瀵瑰簲鐨勬暟鎹畃osition璁句笂Tag锛屽鏋滄槸涓嶅浐瀹歭ayout锛屽啀鎶妉ayoutId璁句笂tag
		}
		else
		{
			// 濡傛灉view涓嶆槸null锛屽垯杩涜涓や釜鍒ゆ柇锛�
			// 1,view鐨刾osition鏄惁鍜屽綋鍓峱osition涓�嚧锛屽鏋滀竴鑷寸洿鎺ヨ繑鍥炪�
			// 2锛寁iew鐨刲ayout鏄惁鍜屽綋鍓嶉」鐨刲ayout鐨刬d涓�嚧锛屽鏋滀竴鑷达紝鐩存帴鏍规嵁鏁版嵁婧愯繘琛岄〉闈㈠厓绱犳搷浣滐紱濡傛灉涓嶄竴鑷达紝鍒欑敓鎴愭柊layout鐨剉iew,鐒跺悗杩涜椤甸潰鎿嶄綔
			int convertViewPosition = Integer.parseInt(convertView.getTag(R.id.common_adapter_position).toString());// 浠巘ag涓幏鍙栬
			if (convertViewPosition == position)
				return convertView;
			else
				setDataInternal(position, convertView, layoutId);
		}
		return convertView;
	}

	private void setDataInternal(int position, View convertView, int itemLayoutId) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if (layoutId == 0) convertView.setTag(R.id.common_adapter_layout_id, itemLayoutId);
		convertView.setTag(R.id.common_adapter_position, position);
		T item = data.get(position);
		bindData(convertView, item);
		setViewData(convertView, item);
	}

	private void bindData(View convertView, T item) throws IllegalAccessException, InvocationTargetException
	{
		ArrayList<View> controls = VisualHelper.getAllChildrenControls(convertView);
		for (View controlItem : controls)
		{
			Object tag = controlItem.getTag();
			if (!checkBindedTag(tag)) continue;			
			for (TagBindingParam vmModel : ((TagBinding)tag).Operations)
				vmModel.Method.invoke(controlItem, vmModel.Field.get(item));
		}
	}

	private boolean checkBindedTag(Object tag)//鏄惁鏈夋晥鐨勮В鏋愯繃鐨凾ag
	{
		return tag != null && tag instanceof TagBinding;
	}

	public void setViewData(View convertView, T t)
	{
		// 杩欎釜鏄富瑕佸鎴烽�杈戝疄鐜版柟娉�
	}

	private View createView(int itemLayout) throws NoSuchFieldException, NoSuchMethodException
	{
		View control = LayoutInflater.from(context).inflate(itemLayout, null);
		bindTag(control);
		return control;
	}

	private void bindTag(View control) throws NoSuchFieldException, NoSuchMethodException
	{
		ArrayList<View> controls = VisualHelper.getAllChildrenControls(control);
		for (View controlItem : controls)
		{
			Object tag = controlItem.getTag();
			if (!checkOriginalTag(tag)) continue;
			bindTagSingleControl(controlItem, tag.toString());
		}
	}
	
	private boolean checkOriginalTag(Object tag)//鏄惁鏈夋晥鐨勬湭瑙ｆ瀽杩囩殑Tag
	{
		return tag != null && !(tag instanceof TagBinding);
	}

	private void bindTagSingleControl(View controlItem, String tag) throws NoSuchFieldException, NoSuchMethodException
	{
		TagBinding tagBinding = new TagBinding();
		for (TagBindingParamText tagInfo : BindingAnalyst.loadBindingParamText(tag))
			tagBinding.Operations.add(loadBindingParam(controlItem, tagInfo));
		controlItem.setTag(tagBinding);		
	}

	private TagBindingParam loadBindingParam(View controlItem, TagBindingParamText tagInfo) throws NoSuchFieldException, NoSuchMethodException
	{
		TagBindingParam vmModel = new TagBindingParam();
		vmModel.Field = modelClass.getField(tagInfo.FieldNameString);
		vmModel.Method = ReflectHelper.getMethodByClassAndType(controlItem.getClass(), vmModel.Field.getType(), tagInfo.MethodNameString);
		return vmModel;
	}

}
