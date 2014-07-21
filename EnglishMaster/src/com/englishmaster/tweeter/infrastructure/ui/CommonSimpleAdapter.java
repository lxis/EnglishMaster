package com.englishmaster.tweeter.infrastructure.ui;

import java.util.ArrayList;



import com.englishmaster.tweeter.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

//李享写的，比较糙，别用
public abstract class CommonSimpleAdapter<T> extends BaseAdapter//T是列表数据源的类型，Adapter继承自常用的BaseAdapter，试图改进使用的效率和质量
{
	protected ArrayList<T> data;//列表需遍历的数据源
	protected Context context;//Adapter进行一些界面操作所依赖的界面上下文
	protected int layoutId;//当前列表项的对应Layout的ID。如果为0，则当前列表项为不固定layout(如RouteSearch的结果是几个list拼成的)

	//这种不固定Layout的使用方式邓宏认为性能还不够好，先不要用
	public CommonSimpleAdapter(ArrayList<T> data, Context context)//这个构造函数是不固定layout用的
	{
		this.data = data;
		this.context = context;
	}
	
	public CommonSimpleAdapter(ArrayList<T> data,Context context,int layoutId)//这个构造函数是固定layout用的
	{
		this.data = data;
		this.context = context;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount()//通过默认实现简化了调用
	{
		return data.size();
	}

	@Override
	public Object getItem(int position)//通过默认实现简化了调用
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position)//通过默认实现简化了调用
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)//GetView是整个adapter处理的核心
	{
		if (data.get(position) == null) return null;//先检查数据是否存在
		if (convertView == null)//如果当前view是null，则开始创建view
		{
			int itemLayoutId = getViewLayoutId(data.get(position));//根据该项数据和用户定义的逻辑获取该项应该生成的layout。如果是固定layout，则直接返回layout的id
			convertView = createView(itemLayoutId);//创建view
			setDataInternal(position, convertView, itemLayoutId);//这里执行客户逻辑，根据数据对view进行操作。在创建完成之后，把该view对应的数据position设上Tag，如果是不固定layout，再把layoutId设上tag
		}
		else
		{
			//如果view不是null，则进行两个判断：
			//1,view的position是否和当前position一致，如果一致直接返回。
			//2，view的layout是否和当前项的layout的id一致，如果一致，直接根据数据源进行页面元素操作；如果不一致，则生成新layout的view,然后进行页面操作			
			int convertViewPosition = Integer.parseInt(convertView.getTag(R.id.common_adapter_position).toString());//从tag中获取该
			if (convertViewPosition == position)
				return convertView;
			else
			{
				int itemLayoutId = getViewLayoutId(data.get(position));
				if(layoutId == 0)
				{
					Object converViewLayoutTag = convertView.getTag(R.id.common_adapter_layout_id);
					int convertLayoutId = Integer.parseInt(converViewLayoutTag.toString());
					if (convertLayoutId != layoutId) 
						convertView = createView(itemLayoutId);
				}
				setDataInternal(position, convertView, itemLayoutId);
			}
		}
		return convertView;
	}

	private void setDataInternal(int position, View convertView, int itemLayoutId)
	{
		if(layoutId == 0)
			convertView.setTag(R.id.common_adapter_layout_id, itemLayoutId);
		convertView.setTag(R.id.common_adapter_position, position);
		setViewData(convertView, data.get(position));
	}

	public int getViewLayoutId(T item) //用户可以重写这个方法来应对view的layout不一致的情况
	{
		return layoutId;
	}

	public abstract void setViewData(View convertView, T t);//这个是主要客户逻辑实现方法

	private View createView(int itemLayout)
	{
		return LayoutInflater.from(context).inflate(itemLayout, null);
	}
}
