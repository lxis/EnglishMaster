package com.englishmaster.tweeter.presentation.ui.views;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.BindingAdapter;
import com.englishmaster.tweeter.presentation.ui.viewmodels.BindItemViewModel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Space;

public class BindingActivity extends Activity
{
	ArrayList<BindItemViewModel> data = new ArrayList<BindItemViewModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.binding_activity);		
		InitControl();
		InitData();
		BindData();
	}

	private void BindData()
	{
		// TODO Auto-generated method stub		
	}

	private void InitData()
	{
		data.add(new BindItemViewModel("haha"));
		data.add(new BindItemViewModel("hehe"));		
	}

	private void InitControl()
	{
		ListView list = (ListView)findViewById(R.id.listViewBinding);
		BindingAdapter<BindItemViewModel> adapter = new BindingAdapter<BindItemViewModel>(data, BindingActivity.this, R.layout.binding_list_item, BindItemViewModel.class);
		list.setAdapter(adapter);		
		
	}
}
