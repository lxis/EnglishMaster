package com.englishmaster.tweeter.presentation.ui.views;

import java.util.ArrayList;
import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.*;
import com.englishmaster.tweeter.presentation.ui.viewmodels.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BindingActivity extends Activity
{
	ArrayList<BindItemViewModel> data = new ArrayList<BindItemViewModel>();
	BindViewModel viewModel = new BindViewModel("feifei", "chengong", "namanama");

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ViewModelBinder.Bind(viewModel, R.layout.binding_activity, BindingActivity.this);
		new BindingAdapter<BindItemViewModel>(data, BindingActivity.this, R.layout.binding_list_item, BindItemViewModel.class).bindAdapter(R.id.listViewBinding);
		InitData();
	}

	public void Click(View view)
	{
		viewModel.SetContent("wangwang");
		viewModel.SetTitle("uuu");
	}

	private void InitData()
	{
		data.add(new BindItemViewModel("haha", "Content"));		
		data.add(new BindItemViewModel("hehe", "Content too"));
	}
}
