package com.englishmaster.tweeter.ui;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.domain.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {

	ArrayList<Article> data;
	Context context;
	
	public MainListAdapter(ArrayList<Article> data,Context context)
	{
		this.data = data;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		convertView = LayoutInflater.from(context).inflate(R.layout.listitem_main_list, null);
		TextView textView1 = (TextView)convertView.findViewById(R.id.text1);
		textView1.setText(data.get(position).Content);
		
		return convertView;
	}

}
