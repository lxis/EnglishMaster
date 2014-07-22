package com.englishmaster.tweeter.presentation.ui.views;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandler;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BindingAdapter;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.ViewModelBinder;
import com.englishmaster.tweeter.presentation.ui.viewmodels.BindItemViewModel;
import com.englishmaster.tweeter.presentation.ui.viewmodels.BindViewModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Space;

public class BindingActivity extends Activity
{
	ArrayList<BindItemViewModel> data = new ArrayList<BindItemViewModel>();

	BindViewModel viewModel = new BindViewModel("feifei", "chengong", "namanama");

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ViewModelBinder.Bind(viewModel, R.layout.binding_activity, BindingActivity.this);
		InitControl();
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
		final BindItemViewModel viewModel = new BindItemViewModel("hehe", "Content too");
		data.add(viewModel);
	}

	private void InitControl()
	{
		new BindingAdapter<BindItemViewModel>(data, BindingActivity.this, R.layout.binding_list_item, BindItemViewModel.class).bindAdapter(R.id.listViewBinding);
	}
}
